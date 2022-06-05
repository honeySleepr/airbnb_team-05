package project.airbnb.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.bnb.BnbType;

@Component
@Slf4j
public class DataProcessor {

	public static final String RESOURCE_PATH = "src/main/resources/";
	public static final String GEOCODE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
	public static final String S3_IMAGE_URL = "http://S3dummy.com/";

	public final String clientId;
	public final String clientSecret;
	public final BnbRepository bnbRepository;

	public final List<String> description = new ArrayList<>(List.of(
		"대통령의 집무 등에 사용되는 본 건물로 1991년 9월에 신축되었다. 대통령 집무실이 있으며 국무회의가 열리는 장소이다. 과거에는 수석보좌관 회의도 이곳에서 열렸다. 공간이 필요 이상으로 넓고 업무와 소통을 위한 공간이라기보다 의전만을 위한 건축물에 더 가깝다는 비판도 존재하여 문재인 대통령이 취임하면서 공식적인 업무만 이곳에서 보기로 하였다",
		"전통 목구조와 궁궐 건축양식을 기본으로 하고 있는데 2층 본채를 중심으로 좌우에 각각 단층의 별채가 배치되어 있고 팔작 지붕을 올린 뒤 한식 청기와 15만 장을 이었다. 지붕에 올린 청기와 모습을 자세히 보면 수키와와 암키와 수막새와 암막새 등 일반 기와 이외에 잡상(雜象)·취두(鷲頭)·용두(龍頭)·토수(吐首) 등 궁궐에서 볼 수 있는 장식 기와를 사용했다.",
		"대규모 회의 외국 국빈들 영접 여러 공식 행사 등을 개최하는 건물로 1978년 12월에 준공됐다. 현재 청와대 경내의 현대식 건물 중 가장 오래된 건물이다. 1층은 대접견실로 외국 국빈의 접견 행사를 치르며 2층은 대규모 오찬 및 만찬 행사를 하는 곳으로 무궁화와 월계수 등으로 장식되어 있다.",
		"대통령과 그 가족이 생활했던 곳으로 1990년에 준공되었다. 대통령 가족의 사적인 공간이기에 청와대 내에서도 가장 폐쇄적인 공간으로 취재진에게도 잘 공개되지 않았다. 이후 노무현이 재임 중인 2003년 3월과 11월에 관저 입구를 공개한 적이 있었으며 이명박도 재임 중에 가족 생활과 관련한 모습을 공개한 적이 있다. 문재인도 재임 중에 관저 입구에서 출근하는 모습을 몇 번 공개했었다.",
		"청와대 참모들이 일하는 곳으로 총 3동으로 나뉘어 있다. 이명박 대통령 재임시에 위민관(爲民館)으로 변경되었으며 문재인 대통령이 취임하면서 노무현 정권 시절 증축 당시의 이름이었던 여민관으로 환원되었다. 여민관은 《맹자》에 나오는 구절인 여민동락(與民同樂)에서 취한 것으로 백성과 즐거움을 함께 한다는 뜻이다. 한편 위민은 국민을 위한 정치를 펼친다는 뜻으로 추측한다.",
		"청와대의 정원인 녹지원 뒤에 위치한 전통 한옥으로 외빈 접견 등에 사용되는 곳이다. 상춘재의 뜻은 봄이 늘 계속되는 집이다. 지금의 상춘재 자리는 일제 시대 때 일본식 목조 건물인 매화실(梅花室)이라는 별관이 있던 자리다. 약 66m2 규모의 크기였다. 정부 수립 이후 상춘실(常春室)로 바뀌었는데 다과나 만찬 행사 등 의전용 건물로 쓰였다.",
		"1993년 7월 기존에 존재하던 궁정동 안가를 철거한 뒤 만든 시민공원이다.[30] 안가 5채를 헐어 조성했으며 10560m2 규모다. 동산의 정원은 청와대 쪽을 향하고 있으며 후문은 창의문 쪽을 바라보고 있다. 동산 가운데에는 중앙광장이 있는데 이곳의 가운데에는 궁정동을 의미하는 우물 정(井) 자 모양의 우물이 있다. 또한 박정희가 최후를 맞이한 자리인 과거의 안가 나동 대연회장 자리에는 길이 30m 높이 3m의 돌담이 위치하고 있다."
	));

	public final List<String> host = new ArrayList<>(List.of(
		"검봉", "노리", "데이먼", "땃쥐", "로니", "루이", "반스", "벅픽", "산토리", "선을로", "케이", "쿠킴", "테리", "포키", "피오",
		"후", "Ader", "BC", "Dave", "donggi", "Hanse", "Heyho", "Hwi", "ikjo", "Jay", "Jerry", "Jun",
		"Lee", "Lucid", "Meenzino", "Miller", "Nathan", "Phil", "Riako", "sally", "Sammy", "Shine",
		"Tany", "Yan", "zzangmin"
	));

	public DataProcessor(@Value("${naverApi.client_id}") String clientId,
		@Value("${naverApi.client_secret}") String clientSecret,
		BnbRepository bnbRepository) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.bnbRepository = bnbRepository;
	}

	@PostConstruct
	public void action() {
		// 필요 시에만 사용하려고 만든 것
		//		try {
		//						createXYCsv();
		//						saveDummyBnb();
		//						createInitDataFile();
		//		} catch (IOException e) {
		//			log.debug("파일 경로 확인. 지정 경로 : {}", RESOURCE_PATH);
		//			e.printStackTrace();
		//		}
	}

	private void createInitDataFile() throws IOException {
		FileInputStream file = new FileInputStream(RESOURCE_PATH + "hotel_data.xlsx");
		Sheet sheet;
		try (Workbook workbook = new XSSFWorkbook(file)) {
			sheet = workbook.getSheetAt(0);
		}

		Random random = new Random();
		List<String[]> data = new ArrayList<>();

		for (int i = 3; i < sheet.getLastRowNum() + 1; i++) {
			// [row.getCell index : data] =  1:지역1(서울시), 2:지역2(강남구), 3:업체명, 4:전체주소, 5:위도, 6:경도
			Row row = sheet.getRow(i);
			data.add(new String[]{
				"'" + row.getCell(3).getStringCellValue() + "'",
				"'한국'",
				"'" + row.getCell(1).getStringCellValue() + "'",
				"'" + row.getCell(2).getStringCellValue() + "'",
				"'" + row.getCell(4).getStringCellValue() + "'",
				String.valueOf(row.getCell(5).getNumericCellValue()),
				String.valueOf(row.getCell(6).getNumericCellValue()),
				String.valueOf(1 + random.nextInt(3)),
				String.valueOf(1 + random.nextInt(4)),
				String.valueOf(1 + random.nextInt(2)),
				"'" + String.valueOf(BnbType.values()[random.nextInt(3)]) + "'",
				"'" + description.get(random.nextInt(description.size())) + "'",
				String.valueOf(10_000 + 1000L * random.nextInt(991)),
				"'" + host.get(random.nextInt(host.size())) + "'",
				String.valueOf(2 + random.nextInt(5)),
				String.valueOf(3 + (Math.round(random.nextInt(21) * 0.1 * 10) / 10d)),
				String.valueOf(random.nextInt(1000)),
				"'" + (14 + random.nextInt(5)) + ":00" + "'",
				"'" + (10 + random.nextInt(4)) + ":00" + "'"
			});
		}

		File initDataFile = new File(RESOURCE_PATH + "bnbInitData.csv");
		try (PrintWriter pw = new PrintWriter(initDataFile)) {
			data.stream()
				.map(this::convertToCSV)
				.forEach(pw::println);
		}
	}

	public String convertToCSV(String[] data) {
		return Stream.of(data)
			.collect(Collectors.joining(",", "(", ")"));
	}

	// Naver API를 이용해 주소를 위/경도로 변환하여 CSV 파일에 저장한다
	// dummy data 용 xlsx 파일에 넣을 위/경도 정보 얻기 위해 사용
	private void createXYCsv() throws IOException {
		// hotel_data.xlsx 의 첫번째 시트를 사용한다
		FileInputStream file = new FileInputStream(RESOURCE_PATH + "hotel_data.xlsx");
		Sheet sheet;
		try (Workbook workbook = new XSSFWorkbook(file)) {
			sheet = workbook.getSheetAt(0);
		}

		// 위/경도 정보를 담는 리스트
		List<String[]> coordinates = new ArrayList<>();

		// 데이터가 담겨있는 3행 부터 마지막 행까지 순회한다
		for (int i = 3; i < sheet.getLastRowNum(); i++) {
			// i 행 데이터를 가져온다
			// [컬럼 index : data] =  1:지역1(서울시), 2:지역2(강남구), 3:업체명, 4:전체주소
			Row row = sheet.getRow(i);

			// Naver GeoCode API 사용을 위해 인증 정보를 지정된 양식에 따라 Header에 담는다
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
			headers.set("X-NCP-APIGW-API-KEY", clientSecret);
			HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

			try {
				// 주소 -> 위/경도 변환을 위해 전체주소(getCell(4))를 담아 요청을 보낸다.
				NaverApiResponse response = new RestTemplate()
					.exchange(GEOCODE_URL + row.getCell(4).getStringCellValue(),
						HttpMethod.GET, request, NaverApiResponse.class).getBody();

				// API response에서 x(경도),y(위도)를 가져와서 업체명과 함께 List에 담는다
				Coordinate coordinate = response.getCoordinates().get(0);
				coordinates.add(new String[]{row.getCell(4).getStringCellValue(),
					String.valueOf(coordinate.getLatitude()),
					String.valueOf(coordinate.getLongitude())});
			} catch (Exception e) {
				// 보내진 주소가 잘못되어 응답이 오지 않는 경우 위/경도 대신 "에러"가 저장되도록 한다
				coordinates.add(new String[]{"빈 셀", "에러", "에러"});
			}
			log.debug("{}", i);
		}

		// coordinates List 의 내용을 xy.csv 파일에 저장한다
		File csvOutputFile = new File(RESOURCE_PATH + "xy.csv");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			coordinates.stream()
				.map(this::convertToCSV)
				.forEach(pw::println);
		}
	}

}

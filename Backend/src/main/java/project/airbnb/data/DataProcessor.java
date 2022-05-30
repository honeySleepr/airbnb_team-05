package project.airbnb.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
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
import project.airbnb.bnb.Address;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbOption;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.bnb.BnbType;
import project.airbnb.bnb.CheckInOutTime;
import project.airbnb.bnbImage.BnbImage;

@Component
@Slf4j
public class DataProcessor {

	public static final String RESOURCE_PATH = "Backend/src/main/resources/";
	public static final String GEOCODE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
	public static final String S3_IMAGE_URL = "";

	public final String clientId;
	public final String clientSecret;
	public final BnbRepository bnbRepository;

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
		//			createXYCsv();
		//			saveDummyBnb();
		//		} catch (IOException e) {
		//			log.debug("파일 경로 확인. 지정 경로 : {}", RESOURCE_PATH);
		//			e.printStackTrace();
		//		}
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
		for (int i = 3; i < sheet.getLastRowNum() + 1; i++) {
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
				ApiResponse response = new RestTemplate()
					.exchange(GEOCODE_URL + row.getCell(4).getStringCellValue(),
						HttpMethod.GET, request, ApiResponse.class).getBody();

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

	public String convertToCSV(String[] data) {
		return Stream.of(data)
			.collect(Collectors.joining(","));
	}

	private void saveDummyBnb() throws IOException {
		// dummy data 가 저장되어 있는 hotel_data.xlsx 파일을 읽어온다
		FileInputStream file = new FileInputStream(RESOURCE_PATH + "hotel_data.xlsx");
		Sheet sheet;
		try (Workbook workbook = new XSSFWorkbook(file)) {
			// 첫번 째 시트에서 데이터를 가져온다
			sheet = workbook.getSheetAt(0);
		}
		Random random = new Random();

		// 데이터가 담긴 첫 행(3) 부터 N행 까지 조회하며 dummy Bnb를 생성하고 저장한다
		for (int i = 3; i < 5; i++) {

			Row row = sheet.getRow(i);

			// [row.getCell index : data] =  1:지역1(서울시), 2:지역2(강남구), 3:업체명, 4:전체주소, 5:위도, 6:경도
			// 저장된 값은 사용하고, 나머지 값은 랜덤하게 돌린다.
			// TODO: 위도, 경도는 어떻게 저장하는게 좋을지 상의하기
			Bnb bnb = new Bnb(null, new ArrayList<>(), row.getCell(3).getStringCellValue(),
				new Address("한국", row.getCell(1).getStringCellValue(),
					row.getCell(2).getStringCellValue(),
					row.getCell(4).getStringCellValue()),
				20_000 + 1000L * random.nextInt(81),
				new BnbOption(1 + random.nextInt(3), 1 + random.nextInt(4), 1 + random.nextInt(3)),
				new CheckInOutTime(LocalTime.of(15, 0), LocalTime.of(11, 0)),
				BnbType.values()[random.nextInt(3)], "숙소 설명", "호스트명",
				2 + random.nextInt(3),
				3 + (Math.round(random.nextInt(21) * 0.1 * 10) / 10d),
				random.nextInt(1000));

			// 각 숙소당 3개의 랜덤 이미지를 저장한다. S3에 100개의 이미지를 넣고 랜덤으로 돌리고자 한다
			// TODO: S3에 이미지 채워넣고 파일이름 번호순으로 나열하기
			for (int j = 0; j < 3; j++) {
				BnbImage bnbImage = new BnbImage();
				bnbImage.setImageUrl(S3_IMAGE_URL + (1 + random.nextInt(100)));
				bnb.saveBnbImage(bnbImage);
			}

			bnbRepository.save(bnb);
		}
	}
}

package project.airbnb.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import project.airbnb.bnb.Address;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbOption;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.bnb.BnbType;
import project.airbnb.bnb.Time;
import project.airbnb.bnbImage.BnbImage;

@Controller
public class dataController {

	public static final String RESOURCE_PATH = "Backend/src/main/resources/";
	public static final String GEOCODE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
	public static final String S3_IMAGE_URL = "";

	public final String clientId;
	public final String clientSecret;
	public final BnbRepository bnbRepository;

	public dataController(@Value("${naverApi.client_id}") String clientId,
		@Value("${naverApi.client_secret}") String clientSecret,
		BnbRepository bnbRepository) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.bnbRepository = bnbRepository;
	}

	@PostConstruct
	public void insertDummyData() throws IOException {
		FileInputStream file = new FileInputStream(RESOURCE_PATH + "hotel_data.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Random random = new Random();
		for (int i = 3; i < 5; i++) { // 4행(index:3) 부터 데이터

			Row row = sheet.getRow(i);
			// row.getCell -> 1: 지역1(서울특별시), 2: 지역2(강남구), 4: 호텔명, 7: 전체주소(서울특별시 강남구 언주로 640)
			//			System.out.println("row.getCell(1) = " + row.getCell(1));
			//			System.out.println("row.getCell(2) = " + row.getCell(2));
			//			System.out.println("row.getCell(4) = " + row.getCell(4));
			//			System.out.println("row.getCell(7) = " + row.getCell(7));

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
			headers.set("X-NCP-APIGW-API-KEY", clientSecret);

			HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

			ApiResponse body = new RestTemplate()
				.exchange(GEOCODE_URL + row.getCell(7).getStringCellValue(), HttpMethod.GET,
					request, ApiResponse.class)
				.getBody();

			Coordinate coordinate = body.getCoordinates().get(0);

			System.out.println("coordinate.getLatitude() = " + coordinate.getLatitude());
			System.out.println("coordinate.getLongitude() = " + coordinate.getLongitude());

			System.out.println("=====================================");

			Bnb bnb = new Bnb(null, new ArrayList<>(), row.getCell(4).getStringCellValue(),
				3 + (Math.round(random.nextInt(21) * 0.1 * 10) / 10d), random.nextInt(1000),
				new Address("한국", row.getCell(1).getStringCellValue(),
					row.getCell(2).getStringCellValue(),
					row.getCell(7).getStringCellValue()),
				BnbType.values()[random.nextInt(3)], "BC",
				2 + random.nextInt(3),
				new Time(LocalTime.of(15, 00), LocalTime.of(11, 00)),
				new BnbOption(1 + random.nextInt(3), 1 + random.nextInt(4), 1 + random.nextInt(3)),
				"설명", 20_000 + 1000L * random.nextInt(81)
			);
			for (int j = 0; j < 3; j++) {
				BnbImage bnbImage = new BnbImage();
				bnbImage.setImageUrl(S3_IMAGE_URL + (1 + random.nextInt(100)));
				bnb.saveBnbImage(bnbImage);
			}
			bnbRepository.save(bnb);
		}

	}

}

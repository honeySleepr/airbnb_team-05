package project.airbnb.bnb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import io.restassured.RestAssured;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

//Todo: 검색 조회용 db 따로 두기?
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BnbControllerSearchTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	@DisplayName("필수값인 검색 주소 외에 아무런 검색조건이 주어지지 않으면 기본값이 할당된다.")
	public void search_default_value() {

		given()
			.param("searchLocation", "아무주소")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("checkIn", equalTo(String.valueOf(LocalDate.now())))
			.body("checkOut", equalTo(String.valueOf(LocalDate.now().plusDays(7))))
			.body("guestNumber", equalTo(1))
			.body("minFee", equalTo(10_000))
			.body("maxFee", equalTo(1_000_000));

	}

	// 장소는 필수값이기 때문에 이후 테스트부터 "해운대"로 고정한다
	//TODO : 조회되는 bnb 개수를 테스트에서 직접 추출하기?
	@Test
	@DisplayName("사이즈와 페이지를 지정하면 해당되는 데이터만 조회된다.")
	public void search_paging() {
		// "해운대" 숙소는 총 53개
		given()
			.param("searchLocation", "해운대")
			.param("size", "10")
			.param("page", "4")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data", hasSize(10))
			.body("pageSize", is(10))
			.body("isLast", is(false))
			.body("pageNumber", is(4));

		given()
			.param("searchLocation", "해운대")
			.param("size", "10")
			.param("page", "5") // 마지막 페이지
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data", hasSize(3)) // 53개 중 남은 3개만 조회된다
			.body("pageSize", is(10))
			.body("isLast", is(true))
			.body("pageNumber", is(5));

	}

	@Test
	@DisplayName("장소로 검색을 하면 해당 장소를 포함하고 있는 주소를 가진 숙소가 조회된다")
	public void search() {

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data.fullAddress", everyItem(containsString("해운대")));
	}

	@Test
	@DisplayName("금액 범위를 지정하여 검색하면 1박요금이 해당 금액 범위 내에 속하는 숙소만 조회된다")
	public void search_fee() {

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("minFee", "50000")
			.param("maxFee", "200000")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data.fee", everyItem(greaterThanOrEqualTo(50000)))
			.body("data.fee", everyItem(lessThanOrEqualTo(200000)));

	}

	@Test
	@DisplayName("숙박일을 지정하여 검색하면 해당 일자에 예약이 가능한 숙소만 조회된다")
	public void search_date() {
		// 53개의 "해운대" 호텔 중 1개만 7/12~7/14에 예약이 잡혀있다.
		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("checkIn", "2022-07-13")
			.param("checkOut", "2022-07-15")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data", hasSize(52));// 53개 중 1개 예약 불가능

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("checkIn", "2022-07-05")
			.param("checkOut", "2022-07-15")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data", hasSize(52));// 53개 중 1개 예약 불가능

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("checkIn", "2022-07-11")
			.param("checkOut", "2022-07-12")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data", hasSize(53)); // 53개 모두 예약 가능

	}

	@Test
	@DisplayName("인원을 지정하여 검색하면 해당 인원을 허용하는 숙소만 조회된다")
	public void search_number_of_guests() {

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("guestNumber", "6")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data.maxGuestNumber", everyItem(greaterThanOrEqualTo(6)));

	}

	@Test
	@DisplayName("금액범위, 날짜, 인원을 모두 지정하여 검색 시 모든 조건을 만족하는 숙소만 조회된다")
	public void search_integrated() {

		int sizeBeforeDateQuery = given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("minFee", "50000")
			.param("maxFee", "200000")
			.param("guestNumber", "3")
			.when()
			.get("/api/bnbs")
			.then()
			.extract().response().jsonPath().getList("data").size();

		given()
			.param("searchLocation", "해운대")
			.param("size", "100")
			.param("minFee", "50000")
			.param("maxFee", "200000")
			.param("guestNumber", "3")
			.param("checkIn", "2022-07-10")
			.param("checkOut", "2022-07-13")
			.when()
			.get("/api/bnbs")
			.then()
			.log().all()
			.body("data.fee", everyItem(greaterThanOrEqualTo(50000)))
			.body("data.fee", everyItem(lessThanOrEqualTo(200000)))
			.body("data.maxGuestNumber", everyItem(greaterThanOrEqualTo(3)))
			.body("data", hasSize(sizeBeforeDateQuery - 1));
	}
}

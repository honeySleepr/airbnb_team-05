package project.airbnb.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BnbSearchResponse<T> {

	private String searchLocation;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Integer guestNumber;
	private Integer count;
	private T data;

	// Todo : 생성자
}

package project.airbnb.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import project.airbnb.bnb.dto.SearchQueryDto;

@Getter
@AllArgsConstructor
public class BnbSearchResponse<T> {

	private String searchLocation;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Integer guestNumber;
	private Long count;
	private Boolean isLast;
	private Integer pageSize;
	private Integer pageNumber;
	private T data;

	public BnbSearchResponse(SearchQueryDto dto, Page page, T data) {
		this.searchLocation = dto.getSearchLocation();
		this.checkIn = dto.getCheckIn();
		this.checkOut = dto.getCheckOut();
		this.guestNumber = dto.getGuestNumber();
		this.count = page.getTotalElements();
		this.isLast = page.isLast();
		this.pageSize = page.getSize();
		this.pageNumber = page.getNumber();
		this.data = data;
	}

}

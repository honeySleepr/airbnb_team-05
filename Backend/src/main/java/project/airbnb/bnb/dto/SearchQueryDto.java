package project.airbnb.bnb.dto;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class SearchQueryDto {

	@NotBlank
	private String searchLocation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkOut;
	@Min(10000)
	private Integer minFee;
	@Max(1000000)
	private Integer maxFee;
	@Min(1)
	private Integer guestNumber;

}

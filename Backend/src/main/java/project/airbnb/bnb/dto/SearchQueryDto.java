package project.airbnb.bnb.dto;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchQueryDto {

	@NotBlank
	private String searchLocation;
	@NotBlank
	private LocalDate checkIn;
	@NotBlank
	private LocalDate checkOut;
	@Min(12000)
	@NotBlank
	private Integer minFee;
	@Max(1000000)
	@NotBlank
	private Integer maxFee;
	@NotBlank
	private Integer guestNumber;

}


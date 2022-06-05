package project.airbnb.bnb.dto;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	@NotNull
	private LocalDate checkIn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate checkOut;
	@Min(12000)
	@NotNull
	private Integer minFee;
	@Max(1000000)
	@NotNull
	private Integer maxFee;
	@Min(1)
	@NotNull
	private Integer guestNumber;

}


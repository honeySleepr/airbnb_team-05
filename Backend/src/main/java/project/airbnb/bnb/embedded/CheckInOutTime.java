package project.airbnb.bnb.embedded;

import java.time.LocalTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckInOutTime {

	private LocalTime checkIn;
	private LocalTime checkOut;

	public CheckInOutTime(LocalTime checkIn, LocalTime checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
}

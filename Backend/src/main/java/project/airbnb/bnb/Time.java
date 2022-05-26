package project.airbnb.bnb;

import java.time.LocalTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Time {

	private LocalTime checkIn;
	private LocalTime checkOut;

	public Time(LocalTime checkIn, LocalTime checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
}

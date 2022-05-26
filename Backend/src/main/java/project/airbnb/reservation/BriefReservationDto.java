package project.airbnb.reservation;

import java.time.LocalDate;
import lombok.Getter;
import project.airbnb.bnb.Address;

@Getter
public class BriefReservationDto {

	private Long bnbId;
	private String bnbName;
	private String imageUrl;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Address address;

}

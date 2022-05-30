package project.airbnb.reservation;

import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;
import project.airbnb.bnb.Address;

@Getter
@ToString
public class ShortReservationDto {

	private Long reservationId;
	private String bnbName;
	private String imageUrl;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Address address;

	// Todo : DTO 만들기, 이대로 괜찮은가
	public ShortReservationDto(Reservation reservation) {
		this.reservationId = reservation.getId();
		this.bnbName = reservation.getBnb().getName();
		this.imageUrl = reservation.getBnb().getImages().get(0).getImageUrl();
		this.checkIn = reservation.getCheckInOutDate().getCheckIn();
		this.checkOut = reservation.getCheckInOutDate().getCheckOut();
		this.address = reservation.getBnb().getAddress();
	}
}

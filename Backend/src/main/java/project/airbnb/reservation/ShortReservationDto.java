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

	public ShortReservationDto(Reservation reservation) {
		this.reservationId = reservation.getId();
		this.bnbName = reservation.getBnb().getName();

		// Todo : List<BnbImage> 대신 List<String>로 바꾸는게 나을까?
		this.imageUrl = reservation.getBnb().getImages().get(0).getImageUrl();

		this.checkIn = reservation.getBookedSchedule().getCheckIn();
		this.checkOut = reservation.getBookedSchedule().getCheckOut();
		this.address = reservation.getBnb().getAddress();
	}
}

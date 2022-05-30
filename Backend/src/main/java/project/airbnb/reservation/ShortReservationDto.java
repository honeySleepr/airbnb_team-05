package project.airbnb.reservation;

import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShortReservationDto {

	private Long reservationId;
	private String bnbName;
	private String imageUrl;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String country;
	private String city;
	private String district;

	public ShortReservationDto(Reservation reservation) {
		this.reservationId = reservation.getId();
		this.bnbName = reservation.getBnb().getName();
		this.imageUrl = reservation.getBnb().getImages().get(0).getImageUrl();
		this.checkIn = reservation.getCheckInOutDate().getCheckIn();
		this.checkOut = reservation.getCheckInOutDate().getCheckOut();
		this.country = reservation.getBnb().getAddress().getCountry();
		this.city = reservation.getBnb().getAddress().getCity();
		this.district = reservation.getBnb().getAddress().getDistrict();
	}
}

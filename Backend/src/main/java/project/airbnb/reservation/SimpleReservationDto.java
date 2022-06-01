package project.airbnb.reservation;

import java.time.LocalDate;
import lombok.Getter;
import project.airbnb.bnb.Address;
import project.airbnb.bnb.Bnb;

@Getter
public class SimpleReservationDto {

	private Long reservationId;
	private String bnbName;
	private String imageUrl;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String country;
	private String city;
	private String district;

	public SimpleReservationDto(Reservation reservation) {
		Bnb bnb = reservation.getBnb();
		Address address = bnb.getAddress();
		this.reservationId = reservation.getId();
		this.bnbName = bnb.getName();
		this.imageUrl = bnb.getImages().get(0).getImageUrl();
		this.checkIn = reservation.getCheckInOutDate().getCheckIn();
		this.checkOut = reservation.getCheckInOutDate().getCheckOut();
		this.country = address.getCountry();
		this.city = address.getCity();
		this.district = address.getDistrict();
	}
}

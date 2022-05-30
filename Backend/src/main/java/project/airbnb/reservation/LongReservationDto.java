package project.airbnb.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import project.airbnb.bnb.Address;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbType;

public class LongReservationDto {

	private Long reservationId;
	private String bnbName;
	private List<String> imageUrls;
	private String country;
	private String city;
	private String district;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String host;
	private BnbType bnbType;
	private Integer numberOfGuests;
	private Long totalFee;


	public LongReservationDto(Reservation reservation) {
		Bnb bnb = reservation.getBnb();
		Address address = bnb.getAddress();
		this.reservationId = reservation.getId();
		this.bnbName = bnb.getName();
		this.imageUrls = bnb.getImages()
			.stream()
			.map(b -> b.getImageUrl())
			.collect(Collectors.toList());
		this.country = address.getCountry();
		this.city = address.getCity();
		this.district = address.getDistrict();
		this.checkIn = reservation.getCheckInOutDate().getCheckIn();
		this.checkOut = reservation.getCheckInOutDate().getCheckOut();
		this.host = bnb.getHost();
		this.bnbType = bnb.getBnbType();
		this.numberOfGuests = reservation.getHeadcount().sum();
		this.totalFee = reservation.getTotalFee();
	}

}

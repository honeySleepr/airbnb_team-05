package project.airbnb;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbType;
import project.airbnb.bnb.embedded.Address;
import project.airbnb.bnb.embedded.BnbOption;
import project.airbnb.bnb.embedded.CheckInOutTime;
import project.airbnb.bnb.embedded.Coordinates;
import project.airbnb.bnbImage.BnbImage;
import project.airbnb.member.Member;
import project.airbnb.reservation.CheckInOutDate;
import project.airbnb.reservation.Headcount;
import project.airbnb.reservation.Reservation;

public class TestDataBuilder {

	public static Bnb getBnbInstance() {
		Bnb bnb = new Bnb(null, new ArrayList<>(), "숙소 이름",
			new Address("한국", "성남시", "분당구", "성남시 분당구 백현로 20"),
			new Coordinates(37.333, 127.444), 50_000L,
			new BnbOption(1, 2, 1),
			new CheckInOutTime(LocalTime.of(15, 0), LocalTime.of(11, 0)), BnbType.HOTEL, "숙소 설명",
			"호스트명",
			3, 4.5d, 200);

		BnbImage bnbImage1 = new BnbImage(null, null, "http://www.naver111.com");
		BnbImage bnbImage2 = new BnbImage(null, null, "http://www.naver222.com");
		bnb.saveBnbImage(bnbImage1);
		bnb.saveBnbImage(bnbImage2);
		return bnb;
	}

	public static Member getMemberInstance(String name) {
		return new Member(null, name);

	}

	public static Reservation getReservationInstance(Member member, Bnb bnb, String checkIn,
		String checkOut) {
		String[] checkInDate = checkIn.split("-");
		String[] checkOutDate = checkOut.split("-");
		return new Reservation(null, member, bnb,
			new CheckInOutDate(LocalDate.of(2022, Integer.parseInt(checkInDate[0]),
				Integer.parseInt(checkInDate[1])),
				LocalDate.of(2022, Integer.parseInt(checkOutDate[0]),
					Integer.parseInt(checkOutDate[0]))),
			new Headcount(2, 2, 0), 200_000L);
	}
}

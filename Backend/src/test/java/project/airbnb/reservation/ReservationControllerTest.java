package project.airbnb.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.bnb.Address;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbOption;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.bnb.BnbType;
import project.airbnb.bnb.CheckInOutTime;
import project.airbnb.bnbImage.BnbImage;
import project.airbnb.bnbImage.BnbImageRepository;
import project.airbnb.member.Member;
import project.airbnb.member.MemberRepository;

@SpringBootTest
@Transactional
class ReservationControllerTest {

	@Autowired
	private BnbRepository bnbRepository;
	@Autowired
	private BnbImageRepository bnbImageRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ReservationController reservationController;


	@BeforeEach
	void setUp() {
		BnbImage bnbImage1 = new BnbImage(null, null, "http://www.naver111.com");
		BnbImage bnbImage2 = new BnbImage(null, null, "http://www.naver222.com");

		Bnb bnb = new Bnb(null, new ArrayList<>(), "숙소 이름",
			new Address("한국", "성남시", "분당구", "성남시 분당구 백현로 20"),
			50_000L, new BnbOption(1, 2, 1),
			new CheckInOutTime(LocalTime.of(15, 0), LocalTime.of(11, 0)),
			BnbType.HOTEL, "숙소 설명", "호스트명", 3,
			4.5d, 200);
		bnb.saveBnbImage(bnbImage1);
		bnb.saveBnbImage(bnbImage2);
		bnbRepository.save(bnb);

		Member member = new Member(null);

		Reservation reservation = new Reservation(null, member, null,
			new CheckInOutDate(LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 1, 6)),
			new Headcount(2, 2, 0), 800_000L);
		reservation.saveBnb(bnb);
		reservation.saveMember(member);

		reservationRepository.save(reservation);
		memberRepository.save(member);

	}

	// Data JPA의 query method에 대한 이해를 위한 테스트
	@Test
	@DisplayName("저장된 Reservation Entity가 findById를 통해 정상적으로 조회 된다")
	public void saveMember() {
		// given

		List<ShortReservationDto> list = reservationController.showList();
		for (ShortReservationDto dto : list) {
			System.out.println("dto = " + dto);
		}
		ShortReservationDto dto = list.get(0);

		// then
		assertThat(dto.getBnbName()).isEqualTo("숙소 이름");
		assertThat(dto.getImageUrl()).isEqualTo("http://www.naver111.com");
	}

}

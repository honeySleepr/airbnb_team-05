//package project.airbnb.reservation;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import project.airbnb.bnb.Address;
//import project.airbnb.bnb.Bnb;
//import project.airbnb.bnb.BnbOption;
//import project.airbnb.bnb.BnbRepository;
//import project.airbnb.bnb.BnbType;
//import project.airbnb.bnb.Time;
//import project.airbnb.bnbImage.BnbImage;
//import project.airbnb.bnbImage.BnbImageRepository;
//import project.airbnb.member.Member;
//import project.airbnb.member.MemberRepository;
//
//@SpringBootTest
//@Transactional
//class ReservationControllerTest {
//
//	@Autowired
//	private BnbRepository bnbRepository;
//	@Autowired
//	private BnbImageRepository bnbImageRepository;
//	@Autowired
//	private MemberRepository memberRepository;
//	@Autowired
//	private ReservationRepository reservationRepository;
//	@Autowired
//	private ReservationController reservationController;
//
//
//	@BeforeEach
//	void setUp() {
//		BnbImage bnbImage = new BnbImage(null, null, "http://www.naver.com");
//
//		Bnb bnb = new Bnb(null, new ArrayList<>(), "name1", 5.0, 10,
//			new Address("한국", "서울시", "강남구", "양재로 29"),
//			BnbType.GUESTHOUSE, "BC", 4,
//			new Time(LocalTime.of(16, 30), LocalTime.of(11, 00)),
//			new BnbOption(1, 2, 1),
//			"테스트 bnb 설명", 50_000L);
//		bnb.saveBnbImage(bnbImage);
//		bnbRepository.save(bnb);
//
//		Member member = new Member(null, new ArrayList<>());
//
//		Reservation reservation = new Reservation(null, member, null,
//			LocalDate.of(2022, 1, 1),
//			LocalDate.of(2022, 1, 6),
//			2, 2, 0, 800_000L);
//		reservation.setBnb(bnb);
//		reservation.saveMember(member);
//
//		reservationRepository.save(reservation);
//		memberRepository.save(member);
//
//	}
//
//	@Test
//	@DisplayName("회원이 저장되나")
//	public void saveMember() {
//		// given
//
//		// when
//		List<SimpleReservationDto> list = reservationController.list();
//		System.out.println("=================================");
//		list.forEach(System.out::println);
//		System.out.println("=================================");
//
//		// then
//		assertThat(list.get(0).getBnbName()).isEqualTo("name1");
//	}
//
//}

package project.airbnb.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import project.airbnb.commons.ApiResponse;
import project.airbnb.member.Member;
import project.airbnb.member.MemberRepository;

@SpringBootTest
@Transactional
class ReservationControllerTest {

	private static final Logger log = LoggerFactory.getLogger(ReservationControllerTest.class);
	@Autowired
	private BnbRepository bnbRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ReservationController reservationController;
	private Long reservationId1;
	private Long reservationId2;
	private Long memberId;

	@BeforeEach
	void setUpBnb() {
		Bnb bnb = new Bnb(null, new ArrayList<>(), "숙소 이름",
			new Address("한국", "성남시", "분당구", "성남시 분당구 백현로 20"),
			50_000L, new BnbOption(1, 2, 1),
			new CheckInOutTime(LocalTime.of(15, 0), LocalTime.of(11, 0)),
			BnbType.HOTEL, "숙소 설명", "호스트명", 3,
			4.5d, 200);

		BnbImage bnbImage1 = new BnbImage(null, null, "http://www.naver111.com");
		BnbImage bnbImage2 = new BnbImage(null, null, "http://www.naver222.com");
		bnb.saveBnbImage(bnbImage1);
		bnb.saveBnbImage(bnbImage2);
		bnbRepository.save(bnb);

		Member member = new Member(null);
		Member foundMember = memberRepository.save(member);
		memberId = foundMember.getId();

		Reservation reservation1 = new Reservation(null, member, bnb,
			new CheckInOutDate(LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 1, 6)),
			new Headcount(2, 2, 0), 800_000L);
		Reservation save1 = reservationRepository.save(reservation1);
		reservationId1 = save1.getId();

		Reservation reservation2 = new Reservation(null, member, bnb,
			new CheckInOutDate(LocalDate.of(2022, 3, 3),
				LocalDate.of(2022, 3, 5)),
			new Headcount(2, 0, 0), 200_000L);
		Reservation save2 = reservationRepository.save(reservation2);
		reservationId2 = save2.getId();
	}

	@Test
	@DisplayName("showList 호출 시 지정된 멤버의 모든 예약 내역이 반환된다")
	public void showReservationList() {
		// given

		// when
		ApiResponse<List<ShortReservationDto>> response = reservationController.showList(
			memberId);
		List<ShortReservationDto> list = response.getData();
		for (ShortReservationDto dto : list) {
			log.debug("ShortReservationDto : {}", dto);
		}
		ShortReservationDto dto1 = list.get(0);
		ShortReservationDto dto2 = list.get(1);

		// then
		assertThat(list.size()).isEqualTo(2);
		assertThat(dto1.getReservationId()).isEqualTo(reservationId1);
		assertThat(dto1.getBnbName()).isEqualTo("숙소 이름");
		assertThat(dto1.getImageUrl()).isEqualTo("http://www.naver111.com");

		assertThat(dto2.getReservationId()).isEqualTo(reservationId2);
		assertThat(dto2.getBnbName()).isEqualTo("숙소 이름");
		assertThat(dto2.getImageUrl()).isEqualTo("http://www.naver111.com");

	}

	@Test
	@DisplayName("showDetails 호출 시 지정된 id의 LongReservationDto가 반환된다")
	public void showReservationDetails() {

		// given

		// when
		ApiResponse<LongReservationDto> response = reservationController.showDetails(
			reservationId1);
		LongReservationDto dto = response.getData();
		log.debug("LongReservationDto : {}", dto);

		// then
		assertThat(dto.getReservationId()).isEqualTo(reservationId1);
		assertThat(dto.getBnbName()).isEqualTo("숙소 이름");
		assertThat(dto.getImageUrls().size()).isEqualTo(2);
		assertThat(dto.getImageUrls().get(0)).isEqualTo("http://www.naver111.com");
		assertThat(dto.getImageUrls().get(1)).isEqualTo("http://www.naver222.com");
	}

	@Test
	@DisplayName("존재하지 않은 예약 내역 호출 시 에러를 발생시킨다")
	public void showReservationDetails_error() {
		// given

		// when, then
		assertThatThrownBy(() -> reservationController.showDetails(10000L))
			.isInstanceOf(IllegalArgumentException.class);
	}

}

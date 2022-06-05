package project.airbnb.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.TestDataBuilder;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.member.Member;
import project.airbnb.member.MemberRepository;
import project.airbnb.response.CommonResponse;

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
		Bnb bnb = TestDataBuilder.getBnbInstance();
		bnbRepository.save(bnb);
	
		Member member = TestDataBuilder.getMemberInstance("nori");
		Member foundMember = memberRepository.save(member);
		memberId = foundMember.getId();

		Reservation reservation1 = TestDataBuilder.getReservationInstance(member, bnb, "07-01",
			"07-05");
		reservationId1 = reservationRepository.save(reservation1).getId();

		Reservation reservation2 = TestDataBuilder.getReservationInstance(member, bnb, "07-08",
			"07-09");
		reservationId2 = reservationRepository.save(reservation2).getId();
	}

	@Test
	@DisplayName("showList 호출 시 지정된 멤버의 모든 예약 내역이 반환된다")
	public void showReservationList() {
		// given

		// when
		CommonResponse<List<ReservationSimpleDto>> response = reservationController.showList(
			memberId);

		// then
		List<ReservationSimpleDto> list = response.getData();
		ReservationSimpleDto dto1 = list.get(0);
		ReservationSimpleDto dto2 = list.get(1);
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
		CommonResponse<ReservationDetailDto> response = reservationController.showDetails(
			reservationId1);

		// then
		assertThat(response.getData()).isExactlyInstanceOf(ReservationDetailDto.class);
		ReservationDetailDto data = response.getData();
		assertThat(data.getReservationId()).isEqualTo(reservationId1);
		assertThat(data.getBnbName()).isEqualTo("숙소 이름");
		assertThat(data.getImageUrls().size()).isEqualTo(2);
		assertThat(data.getImageUrls().get(0)).isEqualTo("http://www.naver111.com");
		assertThat(data.getImageUrls().get(1)).isEqualTo("http://www.naver222.com");
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

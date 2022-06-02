package project.airbnb.bnb;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.bnb.dto.DetailBnbDto;
import project.airbnb.bnb.embedded.Address;
import project.airbnb.bnb.embedded.BnbOption;
import project.airbnb.bnb.embedded.CheckInOutTime;
import project.airbnb.bnbImage.BnbImage;
import project.airbnb.member.Member;
import project.airbnb.member.MemberRepository;
import project.airbnb.response.CommonResponse;
import project.airbnb.wishlist.Wish;
import project.airbnb.wishlist.WishRepository;

@SpringBootTest
@Transactional
class BnbControllerTest {

	@Autowired
	private BnbController bnbController;
	@Autowired
	private BnbRepository bnbRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private WishRepository wishRepository;
	private Bnb savedBnb;
	private Member savedMember;

	@BeforeEach
	void setUp() {
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
		savedBnb = bnbRepository.save(bnb);

		Member member = new Member(null, "bc");
		savedMember = memberRepository.save(member);
	}

	@Test
	@DisplayName("showDetails 호출 시 bnb 상세 정보를 반환한다 - wishlist 등록이 안 된 경우")
	public void showDetails_isNotWishList() {
		// given

		// when
		CommonResponse<DetailBnbDto> response = bnbController.showDetails(savedBnb.getId(),
			savedMember.getId());
		// then
		DetailBnbDto data = response.getData();
		assertThat(data.getBnbId()).isEqualTo(savedBnb.getId());
		assertThat(data.getName()).isEqualTo("숙소 이름");
		assertThat(data.getImageUrls().get(0)).isEqualTo("http://www.naver111.com");
		assertThat(data.getImageUrls().get(1)).isEqualTo("http://www.naver222.com");
		assertThat(data.getIsWish()).isFalse();
	}

	@Test
	@DisplayName("대상 bnb를 wishlist 등록 후 showDetails 호출 시 bnb 상세 정보를 반환한다 - wishlist 등록이 된 경우")
	public void showDetails_isWishList() {
		// given

		// when
		wishRepository.save(new Wish(null, savedMember, savedBnb));
		CommonResponse<DetailBnbDto> response = bnbController.showDetails(savedBnb.getId(),
			savedMember.getId());

		// then
		DetailBnbDto data = response.getData();
		assertThat(data.getBnbId()).isEqualTo(savedBnb.getId());
		assertThat(data.getName()).isEqualTo("숙소 이름");
		assertThat(data.getImageUrls().get(0)).isEqualTo("http://www.naver111.com");
		assertThat(data.getImageUrls().get(1)).isEqualTo("http://www.naver222.com");
		assertThat(data.getIsWish()).isTrue();
	}
}

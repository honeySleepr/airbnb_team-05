package project.airbnb.bnb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.bnb.dto.DetailBnbDto;
import project.airbnb.bnb.dto.SearchQueryDto;
import project.airbnb.wishlist.WishRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class BnbService {

	private final BnbRepository bnbRepository;
	private final WishRepository wishRepository;

	public DetailBnbDto showDetails(Long bnbId, Long MemberId) {

		Bnb bnb = bnbRepository.findById(bnbId)
			.orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다"));

		//MemberId, bnbId 둘다 일치하는 bnb가 Wish 테이블에 있는지 확인
		boolean isWish = wishRepository.existsWishByMemberIdAndBnbId(MemberId, bnbId);

		return new DetailBnbDto(bnb, isWish);
	}

	public void showSearchBnbs(SearchQueryDto searchQueryDto) {
		// 유저가 검색한 위치를 포함하는 주소를 가진 숙소를 조회한다.
		// 유저가 입력한 가격 범위 안에 있는 숙소여야 한다.
		// 유저가 입력한 날짜에 이미 예약이 되어 있는 숙소는 제외한다.
		// 유저가 입력한 인원보다 숙소의 최대 인원이 작은 숙소는 제외한다.
	}
}

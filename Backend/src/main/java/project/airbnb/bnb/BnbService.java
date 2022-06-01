package project.airbnb.bnb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.airbnb.bnb.dto.DetailBnbDto;
import project.airbnb.wishlist.WishRepository;

@Service
@RequiredArgsConstructor
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
}

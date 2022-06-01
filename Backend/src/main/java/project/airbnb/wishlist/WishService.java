package project.airbnb.wishlist;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbRepository;
import project.airbnb.member.Member;
import project.airbnb.member.MemberRepository;


@RequiredArgsConstructor
@Transactional
@Service
public class WishService {

	private final WishRepository wishlistRepository;
	private final BnbRepository bnbRepository;
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public List<WishDto> findWishlist(Long id) {
		List<Wish> wishlist = wishlistRepository.findWishlistByMemberId(id);

		return wishlist.stream()
			.map(wish -> new WishDto(wish))
			.collect(Collectors.toList());
	}

	public void saveWish(Long memberId, Long bnbId) {
		if (isExistWish(memberId, bnbId)) {
			throw new IllegalStateException("이미 위시리스트에 포함된 방입니다. bnbId= " + bnbId);
		}

		Bnb bnb = bnbRepository.findById(bnbId)
			.orElseThrow(() -> new NoSuchElementException("Bnb " + bnbId + "을 찾을 수 없습니다."));

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new NoSuchElementException("member " + memberId + "을 찾을 수 없습니다."));

		wishlistRepository.save(new Wish(null, member, bnb));
	}

	public void deleteWish(Long memberId, Long bnbId) {
		Wish wish = wishlistRepository.findWishByMemberIdAndBnbId(memberId, bnbId)
			.orElseThrow(() -> new NoSuchElementException(
				"위시리스트에 포함되지 않은 방입니다. bnbId= " + bnbId));

		wishlistRepository.delete(wish);
	}

	private boolean isExistWish(Long memberId, Long bnbId) {
		return wishlistRepository.existsWishByMemberIdAndBnbId(memberId, bnbId);
	}
}

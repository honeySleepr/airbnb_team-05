package project.airbnb.wishlist;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

	private final WishRepository wishlistRepository;

	//todo 미완성 진행중
	@Transactional(readOnly = true)
	public List<WishDto> findWishlist(Long id) {
		List<Wish> wishlist = wishlistRepository.findWishlistByMemberId(id);

		return wishlist.stream()
			.map(wish -> new WishDto(wish))
			.collect(Collectors.toList());
	}
}

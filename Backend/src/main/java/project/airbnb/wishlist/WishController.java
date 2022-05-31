package project.airbnb.wishlist;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.airbnb.commons.ApiResponse;

@RequiredArgsConstructor
@RestController
public class WishController {

	private final WishService wishlistService;

	@GetMapping("/api/wishlist")
	public ApiResponse<List<WishDto>> findWishlist(@RequestParam Long id) {
		//TODO @RequestParam 으로 받는 id 로그인 구현시 교체
		return new ApiResponse<>(wishlistService.findWishlist(id));
	}

}

package project.airbnb.wishlist;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.airbnb.commons.ApiResponse;

@RequiredArgsConstructor
@RestController
public class WishController {

	private final WishService wishlistService;

	@GetMapping("/api/wishlist")
	public ApiResponse<List<WishDto>> findWishlist(@RequestParam Long memberId) {
		return new ApiResponse<>(wishlistService.findWishlist(memberId));
	}

	@PostMapping("/api/wishlist")
	public void saveWish(@RequestParam Long memberId, @RequestHeader Long bnbId) {
		wishlistService.saveWish(memberId, bnbId);
	}

	// Todo : 200 응답 내리기?
	@DeleteMapping("/api/wishlist")
	public void deleteWish(@RequestParam Long memberId, @RequestHeader Long bnbId) {
		wishlistService.deleteWish(memberId, bnbId);
	}

}

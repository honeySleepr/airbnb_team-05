package project.airbnb.bnb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import project.airbnb.bnb.dto.DetailBnbDto;
import project.airbnb.commons.ApiResponse;

@RestController
@RequiredArgsConstructor
public class BnbController {

	private final BnbService bnbService;

	@GetMapping("/api/bnb/{id}")
	public ApiResponse<DetailBnbDto> showDetails(@PathVariable("id") Long bnbId,
		@RequestHeader("Authorization") Long memberId) {

		return new ApiResponse<>(bnbService.showDetails(bnbId, memberId));
	}
}

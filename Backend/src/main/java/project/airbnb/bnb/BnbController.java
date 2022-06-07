package project.airbnb.bnb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import project.airbnb.bnb.dto.BnbDetailDto;
import project.airbnb.bnb.dto.BnbSimpleDto;
import project.airbnb.bnb.dto.SearchQueryDto;
import project.airbnb.response.BnbSearchResponse;
import project.airbnb.response.CommonResponse;

@RestController
@RequiredArgsConstructor
public class BnbController {

	private final BnbService bnbService;

	@GetMapping("/api/bnbs/{id}")
	public CommonResponse<BnbDetailDto> showDetails(@PathVariable("id") Long bnbId,
		@RequestHeader("Authorization") Long memberId) {

		return new CommonResponse<>(bnbService.showDetails(bnbId, memberId));
	}

	@GetMapping("/api/bnbs")
	public BnbSearchResponse<List<BnbSimpleDto>> showSearchBnbs(@Validated
		SearchQueryDto searchQueryDto, Pageable pageable) {

		return bnbService.showSearchBnbs(searchQueryDto, pageable);
	}

}

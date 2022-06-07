package project.airbnb.bnb;

import java.time.LocalDate;
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

		return bnbService.showSearchBnbs(validateSearchQuery(searchQueryDto), pageable);
	}

	private SearchQueryDto validateSearchQuery(SearchQueryDto searchQueryDto) {
		//todo 아래로직이 여기 있어도 되나? dto안에 넣어주는게 났나 ?
		if (searchQueryDto.getCheckIn() == null) {
			searchQueryDto.setCheckIn(LocalDate.now());
			searchQueryDto.setCheckOut(LocalDate.now().plusDays(7));
		}
		if (searchQueryDto.getMinFee() == null) {
			searchQueryDto.setMinFee(12_000);
			searchQueryDto.setMaxFee(1_000_000);
		}

		if (searchQueryDto.getGuestNumber() == null) {
			searchQueryDto.setGuestNumber(1);
		}

		return searchQueryDto;
	}

}

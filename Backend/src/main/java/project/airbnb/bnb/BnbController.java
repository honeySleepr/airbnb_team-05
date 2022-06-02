package project.airbnb.bnb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import project.airbnb.bnb.dto.DetailBnbDto;
import project.airbnb.bnb.dto.SearchQueryDto;
import project.airbnb.bnb.dto.SimpleBnbDto;
import project.airbnb.response.BnbSearchResponse;
import project.airbnb.response.CommonResponse;

@RestController
@RequiredArgsConstructor
public class BnbController {

	private final BnbService bnbService;

	@GetMapping("/api/bnbs/{id}")
	public CommonResponse<DetailBnbDto> showDetails(@PathVariable("id") Long bnbId,
		@RequestHeader("Authorization") Long memberId) {

		return new CommonResponse<>(bnbService.showDetails(bnbId, memberId));
	}


	@GetMapping("/api/bnbs")

	public BnbSearchResponse<List<SimpleBnbDto>> showSearchBnbs(@Validated
		SearchQueryDto searchQueryDto) {

		bnbService.showSearchBnbs(searchQueryDto);

		return null;
	}










/* /api/bnb/false/{id}
	//RequestParam
	/api/bnb/{id}?checkin=0201&checkout=0203&adult=0

	 검색 쿼리:   주소, 날짜, 인원, 요금
	// Todo : 주소만 검색해서 조회 (필수)
	// Todo : 주소+날짜 검색해서 조회
	// Todo : 주소+날짜+인원 검색해서 조회
	// Todo : 주소+인원 검색해서 조회
*/

}

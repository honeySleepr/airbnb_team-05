package project.airbnb.bnb;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.airbnb.bnb.dto.BnbDetailDto;
import project.airbnb.bnb.dto.BnbSimpleDto;
import project.airbnb.bnb.dto.SearchQueryDto;
import project.airbnb.response.BnbSearchResponse;
import project.airbnb.wishlist.WishRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BnbService {

	private final BnbRepository bnbRepository;
	private final WishRepository wishRepository;

	public BnbDetailDto showDetails(Long bnbId, Long MemberId) {

		Bnb bnb = bnbRepository.findById(bnbId)
			.orElseThrow(() -> new IllegalArgumentException("해당 숙소가 존재하지 않습니다"));

		//MemberId, bnbId 둘다 일치하는 bnb가 Wish 테이블에 있는지 확인
		boolean isWish = wishRepository.existsWishByMemberIdAndBnbId(MemberId, bnbId);

		return new BnbDetailDto(bnb, isWish);
	}

	public BnbSearchResponse<List<BnbSimpleDto>> showSearchBnbs(SearchQueryDto searchQueryDto,
		Pageable pageable) {
		Page<Bnb> page = bnbRepository.search(searchQueryDto, pageable);

		List<BnbSimpleDto> dtos = page.getContent().stream()
			.map(bnb -> new BnbSimpleDto(bnb, searchQueryDto))
			.collect(Collectors.toList());

		return new BnbSearchResponse<>(searchQueryDto, page, dtos);
	}
}

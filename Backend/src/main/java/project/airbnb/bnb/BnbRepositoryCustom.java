package project.airbnb.bnb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.airbnb.bnb.dto.SearchQueryDto;

public interface BnbRepositoryCustom {

	Page<Bnb> search(SearchQueryDto searchQueryDto, Pageable pageable);
}

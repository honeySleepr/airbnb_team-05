package project.airbnb.bnb;

import static org.springframework.util.StringUtils.hasText;
import static project.airbnb.bnb.QBnb.bnb;
import static project.airbnb.reservation.QReservation.reservation;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.airbnb.bnb.dto.SearchQueryDto;

public class BnbRepositoryImpl implements BnbRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public BnbRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Bnb> search(SearchQueryDto dto, Pageable pageable) {
		List<Bnb> content = queryFactory
			.selectFrom(bnb)
			.leftJoin(bnb.reservations, reservation)
			.where(searchLocationContains(dto.getSearchLocation()),
				checkOutLoe(dto.getCheckIn()),
				checkInGoe(dto.getCheckOut()),
				bnbFeeLoe(dto.getMaxFee()),
				bnbFeeGoe(dto.getMinFee()),
				maxGuestNumberGoe(dto.getGuestNumber()))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long count = queryFactory
			.select(bnb.count())
			.from(bnb)
			.where(searchLocationContains(dto.getSearchLocation()),
				checkOutLoe(dto.getCheckIn()),
				checkInGoe(dto.getCheckOut()),
				bnbFeeLoe(dto.getMaxFee()),
				bnbFeeGoe(dto.getMinFee()),
				maxGuestNumberGoe(dto.getGuestNumber()))
			.fetchOne();

		return new PageImpl<>(content, pageable, count);
	}

	private BooleanExpression searchLocationContains(String searchLocation) {
		return hasText(searchLocation) ? bnb.address.fullAddress.contains(searchLocation) : null;
	}

	private BooleanExpression checkOutLoe(LocalDate checkInDate) {
		return checkInDate != null ? reservation.checkInOutDate.checkOut.loe(checkInDate) : null;
	}

	private BooleanExpression checkInGoe(LocalDate checkOutDate) {
		return checkOutDate != null ? reservation.checkInOutDate.checkIn.goe(checkOutDate) : null;
	}

	private BooleanExpression bnbFeeLoe(Integer maxFee) {
		return maxFee != null ? bnb.fee.loe(maxFee) : null;
	}

	private BooleanExpression bnbFeeGoe(Integer minFee) {
		return minFee != null ? bnb.fee.goe(minFee) : null;
	}

	private BooleanExpression maxGuestNumberGoe(Integer guestNumber) {
		return guestNumber != null ? bnb.maxGuestNumber.goe(guestNumber) : null;
	}

}

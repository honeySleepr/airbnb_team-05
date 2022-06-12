package project.airbnb.bnb;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.airbnb.bnb.dto.SearchQueryDto;

import static project.airbnb.bnb.QBnb.bnb;
import static project.airbnb.reservation.QReservation.reservation;

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
			.where(bnb.address.fullAddress.contains(dto.getSearchLocation())
				.and(reservation.checkInOutDate.checkOut.loe(dto.getCheckIn())
					.or(reservation.checkInOutDate.checkIn.goe(dto.getCheckOut()))
					.or(reservation.checkInOutDate.checkIn.isNull()))
				.and(bnb.fee.loe(dto.getMaxFee()))
				.and(bnb.fee.goe(dto.getMinFee()))
				.and(bnb.maxGuestNumber.goe(dto.getGuestNumber())))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long count = queryFactory
			.select(bnb.count())
			.from(bnb)
			.leftJoin(bnb.reservations, reservation)
			.where(bnb.address.fullAddress.contains(dto.getSearchLocation())
				.and(reservation.checkInOutDate.checkOut.loe(dto.getCheckIn())
					.or(reservation.checkInOutDate.checkIn.goe(dto.getCheckOut()))
					.or(reservation.checkInOutDate.checkIn.isNull()))
				.and(bnb.fee.loe(dto.getMaxFee()))
				.and(bnb.fee.goe(dto.getMinFee()))
				.and(bnb.maxGuestNumber.goe(dto.getGuestNumber())))
			.fetchOne();

		return new PageImpl<>(content, pageable, count);
	}

}

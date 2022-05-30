package project.airbnb.reservation;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@EntityGraph(attributePaths = {"bnb"}) // bnb를 join fetch 해온다
	List<Reservation> findReservationsByMemberId(Long memberId);
}

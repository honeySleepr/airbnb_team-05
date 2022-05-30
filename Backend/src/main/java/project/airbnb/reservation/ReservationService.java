package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

	private ReservationRepository reservationRepository;


	public List<ShortReservationDto> showList() {
		return reservationRepository.findReservationsByMemberId(1L)
			.stream()
			.map(ShortReservationDto::new)
			.collect(Collectors.toList());
	}

	public LongReservationDto showDetails(Long reservationId) {
		Reservation reservation = reservationRepository.findReservationById(1L)
			.orElseThrow(IllegalArgumentException::new);
		return new LongReservationDto(reservation);
	}
}

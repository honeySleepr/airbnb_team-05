package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;


	public List<ShortReservationDto> showList(Long id) {
		return reservationRepository.findReservationsByMemberId(id)
			.stream()
			.map(ShortReservationDto::new)
			.collect(Collectors.toList());
	}

	public LongReservationDto showDetails(Long id) {
		Reservation reservation = reservationRepository.findReservationById(id)
			.orElseThrow(IllegalArgumentException::new);
		return new LongReservationDto(reservation);
	}
}

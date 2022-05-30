package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<ShortReservationDto> showList() {

		return reservationRepository.findAll()
			.stream()
			.map(ShortReservationDto::new)
			.collect(Collectors.toList());
	}

	public List<LongReservationDto> showDetails() {
		return null;
	}
}

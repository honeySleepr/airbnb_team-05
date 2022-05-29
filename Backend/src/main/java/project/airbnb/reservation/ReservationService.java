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

	public List<SimpleReservationDto> list() {

		List<Reservation> reservations = reservationRepository.findAll();

		return reservations.stream().map(SimpleReservationDto::new).collect(Collectors.toList());
	}
}

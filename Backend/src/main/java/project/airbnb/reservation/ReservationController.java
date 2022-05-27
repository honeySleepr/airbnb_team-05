package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

	private ReservationRepository reservationRepository;

	public ReservationController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@GetMapping("/")
	public List<BriefReservationDto> list() {

		List<Reservation> all = reservationRepository.findAll();
		List<BriefReservationDto> collect = all.stream()
			.map(BriefReservationDto::new)
			.collect(Collectors.toList());

		return collect;
	}

	@PostConstruct
	public void insertDummyData() {

	}
}

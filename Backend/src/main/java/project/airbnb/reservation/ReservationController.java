package project.airbnb.reservation;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReservationController {

	private ReservationService reservationService;

	@GetMapping("/api/reservations")
	public List<ShortReservationDto> showList() {
		return reservationService.showList();
	}

	@GetMapping("/api/reservations/{id}")
	public List<LongReservationDto> showDetails() {
		return reservationService.showDetails();
	}
}

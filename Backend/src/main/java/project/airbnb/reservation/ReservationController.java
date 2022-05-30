package project.airbnb.reservation;

import java.util.List;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReservationController {

	private ReservationService reservationService;


	@GetMapping("/api/reservations")
	public List<ShortReservationDto> showList() {
		// Todo : 예약 목록 요청시 유저 정보를 받고, 해당 유저의 ID를 통해 reservation을 가져온다
		return reservationService.showList();
	}

	@GetMapping("/api/reservations/{id}")
	public LongReservationDto showDetails(@PathParam("id") Long reservationId) {
		return reservationService.showDetails(reservationId);
	}
}

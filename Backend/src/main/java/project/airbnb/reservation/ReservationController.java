package project.airbnb.reservation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;


	@GetMapping("/api/reservations")
	public List<ShortReservationDto> showList(@RequestHeader("Authorization") Long id) {
		// Todo : 예약 목록 요청시 유저 인증 정보를 받아서 검증한다. 현재는 임시로 Long id만 받는다.
		return reservationService.showList(id);
	}

	@GetMapping("/api/reservations/{id}")
	public LongReservationDto showDetails(@PathVariable("id") Long id) {
		return reservationService.showDetails(id);
	}
}

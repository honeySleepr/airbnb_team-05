package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

	private ReservationRepository reservationRepository;

	// Todo : 예약 목록 요청시 유저 정보를 받는다. 해당 유저의 ID를 통해 reservation을 가져온다
	public List<ShortReservationDto> showList() {

		return reservationRepository.findReservationsByMemberId(1L)
			.stream()
			.map(ShortReservationDto::new)
			.collect(Collectors.toList());
	}

	public List<LongReservationDto> showDetails() {
		return null;
	}
}

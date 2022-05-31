package project.airbnb.reservation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.airbnb.commons.ApiResponse;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;


	public ApiResponse<List<ShortReservationDto>> showList(Long id) {
		List<ShortReservationDto> dtos = reservationRepository.findReservationsByMemberId(id)
			.stream()
			.map(ShortReservationDto::new)
			.collect(Collectors.toList());

		return new ApiResponse<>(dtos);
	}

	public ApiResponse<LongReservationDto> showDetails(Long id) {
		Reservation reservation = reservationRepository.findReservationById(id)
			.orElseThrow(IllegalArgumentException::new);

		return new ApiResponse<>(new LongReservationDto(reservation));
	}


	static class ReservationResponse<T> {

		private T reservations;

	}
}

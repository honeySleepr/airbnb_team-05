package project.airbnb.member;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.airbnb.reservation.Reservation;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 임시 생성 (BC)
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	// 다대일 양방향 Reservation <-> Member
	@OneToMany(mappedBy = "member")
	private List<Reservation> reservations = new ArrayList<>();

	public List<Reservation> getReservations() {
		return reservations;
	}
}

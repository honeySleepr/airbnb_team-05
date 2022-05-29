package project.airbnb.reservation;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.airbnb.bnb.Bnb;
import project.airbnb.member.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Reservation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long id;

	// 다대일 양방향 Reservation <-> Member
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	// 다대일 단방향 Reservation -> Bnb
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bnb_id")
	private Bnb bnb;

	@Embedded
	private BookedSchedule bookedSchedule;

	@Embedded
	private Headcount headcount;

	private Long totalFee;

	public void saveBnb(Bnb bnb) {
		this.bnb = bnb;
	}

	public void saveMember(Member member) {
		this.member = member;
		member.getReservations().add(this);
	}
}

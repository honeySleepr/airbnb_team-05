package project.airbnb.reservation;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.airbnb.bnb.Bnb;
import project.airbnb.member.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 임시 생성 (BC)
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

	// Todo : Embedded를 쓰는게 나을까, 너무 남발하지 않는게 좋을까?
	private LocalDate checkIn;
	private LocalDate checkOut;

	private int adults;
	private int children;
	private int infants;

	private long totalFee;

	public Long getId() {
		return id;
	}

	public Bnb getBnb() {
		return bnb;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setBnb(Bnb bnb) {
		this.bnb = bnb;
	}

	public void saveMember(Member member) {
		this.member = member;
		member.getReservations().add(this);
	}
}

package project.airbnb.reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import project.airbnb.bnb.Bnb;
import project.airbnb.member.Member;

@Entity
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



}

package project.airbnb.bnb;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.airbnb.bnb.embedded.Address;
import project.airbnb.bnb.embedded.BnbOption;
import project.airbnb.bnb.embedded.CheckInOutTime;
import project.airbnb.bnb.embedded.Coordinates;
import project.airbnb.bnbImage.BnbImage;
import project.airbnb.reservation.Reservation;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bnb {

	// 중요 정보
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bnb_id")
	private Long id;
	@OneToMany(mappedBy = "bnb", cascade = CascadeType.ALL)
	private List<BnbImage> images = new ArrayList<>();
	@OneToMany(mappedBy = "bnb")
	private List<Reservation> reservations = new ArrayList<>();

	private String name;
	@Embedded
	private Address address;
	@Embedded
	private Coordinates coordinates;
	private Long fee;

	// 비 중요 정보
	@Embedded
	private BnbOption bnbOption;
	@Embedded
	private CheckInOutTime checkInOutTime;
	@Enumerated(EnumType.STRING)
	private BnbType bnbType;
	private String description;
	private String host;
	private Integer maxGuestNumber;
	private Double rating;
	private Integer reviewCount;

	// 연관 관계 편의 메서드!
	public void saveBnbImage(BnbImage image) {
		image.saveBnb(this);
		images.add(image);
	}

	// 연관 관계 편의 메서드!
	public void saveReservation(Reservation reservation) {
		reservation.saveBnb(this);
		reservations.add(reservation);
	}

}

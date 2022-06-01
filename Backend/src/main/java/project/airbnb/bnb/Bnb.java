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
import project.airbnb.bnbImage.BnbImage;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bnb {

	// 중요 정보
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bnb_id")
	private Long id;
	@OneToMany(mappedBy = "bnb", cascade = CascadeType.ALL)
	private List<BnbImage> images = new ArrayList<>();
	private String name;
	@Embedded
	private Address address;
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
}

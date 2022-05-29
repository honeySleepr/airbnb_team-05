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
@Getter // 임시 생성 (BC)
@AllArgsConstructor // 임시 생성 (BC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bnb {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bnb_id")
	private Long id;

	@OneToMany(mappedBy = "bnb", cascade = CascadeType.ALL)
	private List<BnbImage> images = new ArrayList<>();

	private String name;
	private double rating;
	private int reviewCount;
	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private BnbType bnbType;

	private String host;

	private Integer maxGuestNumber;

	@Embedded
	private Time time;

	@Embedded
	private BnbOption bnbOption;

	private String description;

	private Long fee;

	public void saveBnbImage(BnbImage image) {
		image.setBnb(this);
		images.add(image);
	}


}

package project.airbnb.bnbImage;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.airbnb.bnb.Bnb;

@Entity
@Getter // 임시 생성 (BC)
@AllArgsConstructor // 임시 생성 (BC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BnbImage {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bnb_image_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bnb_id")
	private Bnb bnb;

	private String imageUrl;

	public void setBnb(Bnb bnb) {
		this.bnb = bnb;
	}
}

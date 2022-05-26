package project.airbnb.bnbImage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import project.airbnb.bnb.Bnb;

@Entity
public class BnbImage {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bnb_image_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bnb_id")
	private Bnb bnb;

	private String imageUrl;
}

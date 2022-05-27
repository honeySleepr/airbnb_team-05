package project.airbnb.wishlist;

import lombok.Getter;

@Getter
public class WishDto {

	private Long bnbId;
	private String image_url;
	private double rating;
	private int reviewCount;
	private String bnbName;
	private Long fee;
	private boolean like;

	public WishDto(Long bnbId, String image_url, double rating, int reviewCount,
		String bnbName, Long fee, boolean like) {
		this.bnbId = bnbId;
		this.image_url = image_url;
		this.rating = rating;
		this.reviewCount = reviewCount;
		this.bnbName = bnbName;
		this.fee = fee;
		this.like = like;
	}
}

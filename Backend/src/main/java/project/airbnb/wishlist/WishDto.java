package project.airbnb.wishlist;

import lombok.Data;
import project.airbnb.bnb.Bnb;

@Data
public class WishDto {

	private Long wishlistId;
	private Long bnbId;
	private String imageUrl;
	private double rating;
	private int reviewCount;
	private String bnbName;
	private Long fee;

	public WishDto(Wish wish) {
		Bnb bnb = wish.getBnb();

		this.wishlistId = wish.getId();
		this.bnbId = bnb.getId();
		this.imageUrl = bnb.getImages().get(0).getImageUrl();
		this.rating = bnb.getRating();
		this.reviewCount = bnb.getReviewCount();
		this.bnbName = bnb.getName();
		this.fee = bnb.getFee();
	}

}

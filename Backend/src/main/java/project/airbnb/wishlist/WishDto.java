package project.airbnb.wishlist;

import lombok.Data;

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
		this.wishlistId = wish.getId();
		this.bnbId = wish.getBnb().getId();
		this.imageUrl = wish.getBnb().getImages().get(0).getImageUrl();
		this.rating = wish.getBnb().getRating();
		this.reviewCount = wish.getBnb().getReviewCount();
		this.bnbName = wish.getBnb().getName();
		this.fee = wish.getBnb().getFee();
	}

}

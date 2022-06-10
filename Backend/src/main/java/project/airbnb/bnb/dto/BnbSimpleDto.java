package project.airbnb.bnb.dto;

import java.time.Period;
import lombok.Data;
import project.airbnb.bnb.Bnb;

@Data
public class BnbSimpleDto {

	private Long bnbId;
	private String imageUrl;
	private Double rating;
	private Integer reviewCount;
	private String bnbName;
	private Long fee;
	private Long totalFee;
	private Integer maxGuestNumber;
	private String fullAddress;

	public BnbSimpleDto(Bnb bnb, SearchQueryDto searchQueryDto) {
		this.bnbId = bnb.getId();
		this.imageUrl = bnb.getImages().get(0).getImageUrl();
		this.rating = bnb.getRating();
		this.reviewCount = bnb.getReviewCount();
		this.bnbName = bnb.getName();
		this.fee = bnb.getFee();
		Period stayPeriod = Period.between(searchQueryDto.getCheckIn(),
			searchQueryDto.getCheckOut());
		this.totalFee = stayPeriod.getDays() * bnb.getFee();
		this.maxGuestNumber = bnb.getMaxGuestNumber();
		this.fullAddress = bnb.getAddress().getFullAddress();
	}

}

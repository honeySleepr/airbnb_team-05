package project.airbnb.bnb.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import project.airbnb.bnb.Bnb;
import project.airbnb.bnb.BnbType;
import project.airbnb.bnb.embedded.Address;

@Getter
public class DetailBnbDto {

	private Long bnbId;
	private String name;
	private List<String> imageUrls;
	private String country;
	private String city;
	private String district;
	private Double rating;
	private Integer reviewCount;
	private String host;
	private BnbType bnbType;
	private Integer maxGuestNumber;
	private Long feePerDay;
	private Boolean isWish;

	public DetailBnbDto(Bnb bnb, boolean isWish) {
		Address address = bnb.getAddress();

		this.bnbId = bnb.getId();
		this.name = bnb.getName();
		this.imageUrls = bnb.getImages()
			.stream()
			.map(b -> b.getImageUrl())
			.collect(Collectors.toList());
		this.country = address.getCountry();
		this.city = address.getCity();
		this.district = address.getDistrict();
		this.rating = bnb.getRating();
		this.reviewCount = bnb.getReviewCount();
		this.host = bnb.getHost();
		this.bnbType = bnb.getBnbType();
		this.maxGuestNumber = bnb.getMaxGuestNumber();
		this.feePerDay = bnb.getFee();
		this.isWish = isWish;
	}
}

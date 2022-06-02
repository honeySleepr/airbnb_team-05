package project.airbnb.bnb.dto;

import lombok.Data;

@Data
public class SimpleBnbDto {

	private Long bnbId;
	private String imageUrl;
	private Double rating;
	private Integer reviewCount;
	private String bnbName;
	private Long fee;
	private Long totalFee;

	//TODO 생성자

}

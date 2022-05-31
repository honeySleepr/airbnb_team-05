package project.airbnb.bnb;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BnbOption {

	private int roomCount;
	private int bedCount;
	private int bathroomCount;

	public BnbOption(int roomCount, int bedCount, int bathroomCount) {
		this.roomCount = roomCount;
		this.bedCount = bedCount;
		this.bathroomCount = bathroomCount;
	}
}

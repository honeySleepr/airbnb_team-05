package project.airbnb.bnb;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

	private String country;
	private String city;
	private String district;
	private String road;


	public Address(String country, String city, String district, String road) {
		this.country = country;
		this.city = city;
		this.district = district;
		this.road = road;
	}
}

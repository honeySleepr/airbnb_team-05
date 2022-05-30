package project.airbnb.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Coordinate {

	@JsonProperty(value = "x")
	private Double longitude;
	@JsonProperty(value = "y")
	private Double latitude;
}

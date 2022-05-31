package project.airbnb.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class NaverApiResponse {

	@JsonProperty("addresses")
	private List<Coordinate> coordinates = new ArrayList<>();

}

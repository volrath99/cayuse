package cayuse.exercise.service.imp;

import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.ElevationRetriever;
import cayuse.exercise.service.imp.data.GoogleElevationResponse;

public class GoogleElevationRetriever extends GoogleRetrieverAbstract implements ElevationRetriever {
	private static final String PATH = "/elevation/json";

	public GoogleElevationRetriever(String key) {
		super(key);
	}

	@Override
	public double getElevation(double latitude, double longitude) {
		GoogleElevationResponse response = getTarget().queryParam("locations", latitude + "," + longitude)
				.request(MediaType.APPLICATION_JSON).get(GoogleElevationResponse.class);

		// TODO: Handle response status? WHy doesn't timezone have this?

		// TODO: Convert to feet?
		return response.getResults().get(0).getElevation();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

package cayuse.exercise.service.imp;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.ElevationRetriever;
import cayuse.exercise.service.imp.data.GoogleElevationResponse;

public class GoogleElevationRetriever extends GoogleRetrieverAbstract implements ElevationRetriever {
	// TODO: Put in props?
	private static final String PATH = "/elevation/json";

	public GoogleElevationRetriever(String key) {
		super(key);
	}

	@Override
	public double getElevation(double latitude, double longitude) {
		WebTarget target = getTarget(latitude, longitude);
		GoogleElevationResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(GoogleElevationResponse.class);

		// TODO: Handle response status? WHy doesn't timezone have this?
		
		// TODO: Convert to feet?
		return response.getResults().getElevation();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

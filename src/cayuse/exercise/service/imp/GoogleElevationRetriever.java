package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.ElevationRetriever;
import cayuse.exercise.service.imp.data.GoogleElevationResponse;

public class GoogleElevationRetriever extends GoogleRetrieverAbstract implements ElevationRetriever {
	private static final String PATH = "/elevation/json";

	public GoogleElevationRetriever(Client client, String key) {
		super(client, key);
	}

	@Override
	public double getElevation(double latitude, double longitude) {
		WebTarget target = getTarget().queryParam("locations", latitude + "," + longitude);
		GoogleElevationResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(GoogleElevationResponse.class);

		if (!"OK".equals(response.getStatus())) {
			throw new RuntimeException("Request [" + target.getUri() + "] failed: " + response.getErrorMessage());
		}

		return response.getResults().get(0).getElevation();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

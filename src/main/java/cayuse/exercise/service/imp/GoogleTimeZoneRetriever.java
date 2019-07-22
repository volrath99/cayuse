package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.TimeZoneRetriever;
import cayuse.exercise.service.data.api.google.GoogleTimeZoneResponse;

public class GoogleTimeZoneRetriever extends GoogleRetrieverAbstract implements TimeZoneRetriever {
	private static final String PATH = "/timezone/json";

	public GoogleTimeZoneRetriever(Client client, String key) {
		super(client, key);
	}

	@Override
	public String getTimeZone(double latitude, double longitude) {
		WebTarget target = getTarget().queryParam("location", latitude + "," + longitude).queryParam("timestamp", 0);
		GoogleTimeZoneResponse response = target.request(MediaType.APPLICATION_JSON).get(GoogleTimeZoneResponse.class);

		if (!"OK".equals(response.getStatus())) {
			throw new RuntimeException("Request [" + target.getUri() + "] failed: " + response.getErrorMessage());
		}

		return response.getTimeZoneName();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

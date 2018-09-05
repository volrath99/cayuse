package cayuse.exercise.service.imp;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.TimeZoneRetriever;
import cayuse.exercise.service.imp.data.GoogleTimeZoneResponse;

public class GoogleTimeZoneRetriever extends GoogleRetrieverAbstract implements TimeZoneRetriever {
	// TODO: Put in props?
	private static final String PATH = "/timezone/json";

	public GoogleTimeZoneRetriever(String key) {
		super(key);
	}

	@Override
	public String getTimeZone(double latitude, double longitude) {
		WebTarget target = getTarget(latitude, longitude).queryParam("timestamp", 0);
		GoogleTimeZoneResponse response = target.request(MediaType.APPLICATION_JSON).get(GoogleTimeZoneResponse.class);

		return response.getTimeZoneName();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

package cayuse.exercise.service.imp;

import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.TimeZoneRetriever;
import cayuse.exercise.service.imp.data.GoogleTimeZoneResponse;

public class GoogleTimeZoneRetriever extends GoogleRetrieverAbstract implements TimeZoneRetriever {
	private static final String PATH = "/timezone/json";

	public GoogleTimeZoneRetriever(String key) {
		super(key);
	}

	@Override
	public String getTimeZone(double latitude, double longitude) {
		GoogleTimeZoneResponse response = getTarget().queryParam("location", latitude + "," + longitude)
				.queryParam("timestamp", 0).request(MediaType.APPLICATION_JSON).get(GoogleTimeZoneResponse.class);
		
		// TODO: Check status. Test by changing parameter from location to locations.

		return response.getTimeZoneName();
	}

	@Override
	String getPath() {
		return PATH;
	}

}

package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.imp.data.OpenWeatherMapZipResponse;

public class OpenWeatherMapZipCodeDataRetreiver implements ZipCodeDataRetreiver {
	// TODO: Put in props?
	private static final String URI = "http://api.openweathermap.org/data/2.5/weather";

	private final Client client;
	private final String appId;

	public OpenWeatherMapZipCodeDataRetreiver(String appId) {
		client = ClientBuilder.newClient();
		this.appId = appId;
	}

	@Override
	public ZipCodeMetaData getZipCodeMetaData(int zipCode, TemperatureUnit temperatureUnit) {
		WebTarget target = client.target(URI).queryParam("zip", zipCode + ",us")
				.queryParam("units", temperatureUnit.toString()).queryParam("appid", appId);
		OpenWeatherMapZipResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(OpenWeatherMapZipResponse.class);
		// TODO: cache
		return OpenWeatherMapZipResponseToZipCodeMetaData.transform(response);
	}

}

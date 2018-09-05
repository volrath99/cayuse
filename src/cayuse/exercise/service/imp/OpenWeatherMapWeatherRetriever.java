package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.WeatherRetriever;
import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.imp.data.OpenWeatherMapZipResponse;
import cayuse.exercise.service.imp.transformers.OpenWeatherMapZipResponseToWeatherData;

public class OpenWeatherMapWeatherRetriever implements WeatherRetriever {
	// TODO: Put in props?
	private static final String URI = "http://api.openweathermap.org/data/2.5/weather";
	private static final String COUNTRY_CODE = "us";

	private final Client client;
	private final String appId;

	public OpenWeatherMapWeatherRetriever(String appId) {
		client = ClientBuilder.newClient();
		this.appId = appId;
	}

	@Override
	public WeatherData getWeatherData(int zipCode, TemperatureUnit temperatureUnit) {
		WebTarget target = client.target(URI).queryParam("zip", zipCode + "," + COUNTRY_CODE)
				.queryParam("units", temperatureUnit.toString()).queryParam("appid", appId);
		OpenWeatherMapZipResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(OpenWeatherMapZipResponse.class);
		// TODO: cache
		return OpenWeatherMapZipResponseToWeatherData.transform(response);
	}

}

package main.cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import main.cayuse.exercise.service.WeatherRetriever;
import main.cayuse.exercise.service.data.WeatherData;
import main.cayuse.exercise.service.data.api.openWeather.OpenWeatherMapZipResponse;
import main.cayuse.exercise.service.data.api.openWeather.OpenWeatherMapZipResponseToWeatherData;

public class OpenWeatherMapWeatherRetriever implements WeatherRetriever {
	private static final String URI = "http://api.openweathermap.org/data/2.5/weather";
	private static final String COUNTRY_CODE = "us";
	private static final String TEMPERATURE_UNIT = "metric";

	private final Client client;
	private final String appId;

	public OpenWeatherMapWeatherRetriever(Client client, String appId) {
		this.client = client;
		this.appId = appId;
	}

	@Override
	public WeatherData getWeatherData(String zipCode) {
		WebTarget target = client.target(URI).queryParam("zip", zipCode + "," + COUNTRY_CODE)
				.queryParam("units", TEMPERATURE_UNIT).queryParam("appid", appId);
		OpenWeatherMapZipResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(OpenWeatherMapZipResponse.class);

		return OpenWeatherMapZipResponseToWeatherData.transform(response);
	}

}

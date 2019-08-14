package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cayuse.exercise.conf.ConfigProperties;
import cayuse.exercise.service.WeatherRetriever;
import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.api.openWeather.OpenWeatherMapZipResponse;
import cayuse.exercise.service.transformer.OpenWeatherMapZipResponseToWeatherData;

@Service
public class OpenWeatherMapWeatherRetriever implements WeatherRetriever {
	private static final String COUNTRY_CODE = "us";
	private static final String TEMPERATURE_UNIT = "metric";

	private final Client client;
	private final String appId;
	private final String url;

	public OpenWeatherMapWeatherRetriever(@Autowired Client client, @Autowired ConfigProperties configProperties) {
		this.client = client;
		appId = configProperties.getOpenWeatherMapApi().getApiId();
		url = configProperties.getOpenWeatherMapApi().getUrl();
	}

	@Override
	public WeatherData getWeatherData(String zipCode) {
		WebTarget target = client.target(url).queryParam("zip", zipCode + "," + COUNTRY_CODE)
				.queryParam("units", TEMPERATURE_UNIT).queryParam("appid", appId);
		OpenWeatherMapZipResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(OpenWeatherMapZipResponse.class);

		return OpenWeatherMapZipResponseToWeatherData.transform(response);
	}

}

package cayuse.exercise.service.imp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.imp.data.OpenWeatherMapZipResponse;

public class OpenWeatherMapZipCodeDataRetreiver implements ZipCodeDataRetreiver {
	private final Client client;

	public OpenWeatherMapZipCodeDataRetreiver() {
		client = ClientBuilder.newClient();
	}

	@Override
	public ZipCodeMetaData getZipCodeMetaData(int zipCode) {
		// TODO: get variables
		WebTarget target = client.target("http://api.openweathermap.org").path("/data/2.5/weather")
				.queryParam("zip", zipCode + ",us").queryParam("units", "imperial")
				.queryParam("appid", "d16f2baab3c871115936e2d6cca61968");
		OpenWeatherMapZipResponse response = target.request(MediaType.APPLICATION_JSON)
				.get(OpenWeatherMapZipResponse.class);
		
		// TODO: cache

		// TODO: Check for errors on response!
		
		// TODO: Transform.

		System.out.println(response.getName());
		System.out.println(response.getTemperature());
		System.out.println(response.getLatitude());
		System.out.println(response.getLongitude());

		return new ZipCodeMetaData();
	}

}

package cayuse.exercise.service.transformer;

import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.api.openWeather.OpenWeatherMapZipResponse;

public class OpenWeatherMapZipResponseToWeatherData {
	
	public static WeatherData transform(OpenWeatherMapZipResponse response) {
		WeatherData weatherData = new WeatherData();
		weatherData.setCity(response.getName());
		weatherData.setTemperature(response.getMain().getTemp());
		weatherData.setLatitude(response.getCoord().getLat());
		weatherData.setLongitude(response.getCoord().getLon());
		return weatherData;
	}

}

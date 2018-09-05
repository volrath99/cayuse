package cayuse.exercise.service.imp.transformers;

import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.imp.data.OpenWeatherMapZipResponse;

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

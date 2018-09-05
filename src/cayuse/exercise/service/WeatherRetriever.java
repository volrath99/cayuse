package cayuse.exercise.service;

import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.WeatherData;

public interface WeatherRetriever {
	WeatherData getWeatherData(int zipCode, TemperatureUnit temperatureUnit);
}

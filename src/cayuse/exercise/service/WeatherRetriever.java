package cayuse.exercise.service;

import cayuse.exercise.service.data.WeatherData;

/**
 * Returns weather data in metric units. 
 */
public interface WeatherRetriever {
	WeatherData getWeatherData(int zipCode);
}

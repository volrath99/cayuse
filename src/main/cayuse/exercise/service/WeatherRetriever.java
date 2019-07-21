package main.cayuse.exercise.service;

import main.cayuse.exercise.service.data.WeatherData;

/**
 * Returns weather data in metric units. 
 */
public interface WeatherRetriever {
	WeatherData getWeatherData(String zipCode);
}

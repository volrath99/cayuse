package cayuse.exercise.service;

import cayuse.exercise.service.data.ResponsesToZipCodeMetaData;
import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;

public class ZipCodeDataRetreiver {
	private final WeatherRetriever weatherRetriever;
	private final TimeZoneRetriever timeZoneRetriever;
	private final ElevationRetriever elevationRetriever;
	
	public ZipCodeDataRetreiver(WeatherRetriever weatherRetriever, TimeZoneRetriever timeZoneRetriever, ElevationRetriever elevationRetriever) {
		this.weatherRetriever = weatherRetriever;
		this.timeZoneRetriever = timeZoneRetriever;
		this.elevationRetriever = elevationRetriever;
	}
	
	// TODO: Add logs to obtain frequency and API call timing.
	public ZipCodeMetaData getZipCodeMetaData(int zipCode, TemperatureUnit temperatureUnit) {
		WeatherData weatherData = weatherRetriever.getWeatherData(zipCode, TemperatureUnit.IMPERIAL);
		String timeZone = timeZoneRetriever.getTimeZone(weatherData.getLatitude(), weatherData.getLongitude());
		double elevation = elevationRetriever.getElevation(weatherData.getLatitude(), weatherData.getLongitude());
		
		return ResponsesToZipCodeMetaData.transform(weatherData, timeZone, elevation);
	}
}

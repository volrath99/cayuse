package cayuse.exercise.service.transformer;

import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;

public class ResponsesToZipCodeMetaData {
	
	public static ZipCodeMetaData transform(WeatherData weatherData, String timeZone, double elevation) {
		ZipCodeMetaData metaData = new ZipCodeMetaData();
		metaData.setCity(weatherData.getCity());
		metaData.setTemperature(weatherData.getTemperature());
		metaData.setLatitude(weatherData.getLatitude());
		metaData.setLongitude(weatherData.getLongitude());
		metaData.setTimeZone(timeZone);
		metaData.setElevation(elevation);
		return metaData;
	}

}

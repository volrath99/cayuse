package cayuse.exercise.service.imp.transformers;

import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;

public class ResponsesToZipCodeMetaData {
	
	public static ZipCodeMetaData transform(WeatherData weatherData) {
		ZipCodeMetaData metaData = new ZipCodeMetaData();
		metaData.setCity(weatherData.getCity());
		metaData.setTemperature(weatherData.getTemperature());
		metaData.setLatitude(weatherData.getLatitude());
		metaData.setLongitude(weatherData.getLongitude());
		return metaData;
	}

}

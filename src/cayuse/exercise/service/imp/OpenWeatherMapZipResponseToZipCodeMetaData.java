package cayuse.exercise.service.imp;

import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.imp.data.OpenWeatherMapZipResponse;

public class OpenWeatherMapZipResponseToZipCodeMetaData {
	
	public static ZipCodeMetaData transform(OpenWeatherMapZipResponse response) {
		ZipCodeMetaData metaData = new ZipCodeMetaData();
		metaData.setCity(response.getName());
		metaData.setTemperature(response.getMain().getTemp());
		metaData.setLatitude(response.getCoord().getLat());
		metaData.setLongitude(response.getCoord().getLon());
		return metaData;
	}

}

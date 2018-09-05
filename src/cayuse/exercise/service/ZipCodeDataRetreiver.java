package cayuse.exercise.service;

import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.ZipCodeMetaData;

public interface ZipCodeDataRetreiver {
	public ZipCodeMetaData getZipCodeMetaData(int zipCode, TemperatureUnit temperatureUnit);
}

package cayuse.exercise.service;

import cayuse.exercise.service.data.ZipCodeMetaData;

public interface ZipCodeDataRetreiver {
	ZipCodeMetaData getZipCodeMetaData(int zipCode);
}

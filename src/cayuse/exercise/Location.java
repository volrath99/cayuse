package cayuse.exercise;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

import cayuse.exercise.service.ElevationRetriever;
import cayuse.exercise.service.TimeZoneRetriever;
import cayuse.exercise.service.WeatherRetriever;
import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.imp.GoogleElevationRetriever;
import cayuse.exercise.service.imp.GoogleTimeZoneRetriever;
import cayuse.exercise.service.imp.OpenWeatherMapWeatherRetriever;

public class Location {
	private static final DecimalFormat ELEVATION_FORMAT = new DecimalFormat("#,###");

	public static void main(String[] args) {
		Properties properties = readProperties();
		int zip = getValidatedZip(args);
		ZipCodeDataRetreiver zipCodeDataRetreiver = getZipCodeDataRetreiver(
				properties.getProperty("openWeatherMapApiId"), properties.getProperty("googleApiKey"));
		ZipCodeMetaData zipCodeMetaData = zipCodeDataRetreiver.getZipCodeMetaData(zip);
		printMetaData(zipCodeMetaData);
	}

	public static Properties readProperties() {
		Properties properties = new Properties();
		String workingDir = System.getProperty("user.dir");
		try {
			FileInputStream stream = new FileInputStream(workingDir + "/resources/cayuse.properties");
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			throw new RuntimeException("Unable to read cayuse.properties: " + e.getMessage());
		}
		if (properties.getProperty("openWeatherMapApiId") == null) {
			throw new RuntimeException("openWeatherMapApiId required property.");
		}
		if (properties.getProperty("googleApiKey") == null) {
			throw new RuntimeException("googleApiKey required property.");
		}
		return properties;
	}

	public static int getValidatedZip(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must pass 1 argument: zip-code.");
		}
		String userZipInput = args[0];
		if (userZipInput.length() != 5) {
			throw new IllegalArgumentException("Zip-code [" + userZipInput + "] must be 5 numbers.");
		}
		int zip;
		try {
			zip = Integer.valueOf(userZipInput);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid zip-code [" + userZipInput + "].");
		}
		return zip;
	}

	public static ZipCodeDataRetreiver getZipCodeDataRetreiver(String openWeatherMapApiId, String googleApiKey) {
		WeatherRetriever weatherRetriever = new OpenWeatherMapWeatherRetriever(openWeatherMapApiId);
		TimeZoneRetriever timeZoneRetriever = new GoogleTimeZoneRetriever(googleApiKey);
		ElevationRetriever elevationRetriever = new GoogleElevationRetriever(googleApiKey);

		return new ZipCodeDataRetreiver(weatherRetriever, timeZoneRetriever, elevationRetriever);
	}

	public static void printMetaData(ZipCodeMetaData metaData) {
		System.out.format(getFormattedMetaData(metaData));
	}

	public static String getFormattedMetaData(ZipCodeMetaData metaData) {
		return String.format(
				"At the location %s, the temperature is %1.0f*C, the timezone is %s, and the elevation is %s meters%n",
				metaData.getCity(), metaData.getTemperature(), metaData.getTimeZone(),
				ELEVATION_FORMAT.format(metaData.getElevation()));
	}

}

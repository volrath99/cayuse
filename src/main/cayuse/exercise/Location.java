package main.cayuse.exercise;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import main.cayuse.exercise.service.ElevationRetriever;
import main.cayuse.exercise.service.TimeZoneRetriever;
import main.cayuse.exercise.service.WeatherRetriever;
import main.cayuse.exercise.service.ZipCodeDataRetreiver;
import main.cayuse.exercise.service.data.ZipCodeMetaData;
import main.cayuse.exercise.service.imp.GoogleElevationRetriever;
import main.cayuse.exercise.service.imp.GoogleTimeZoneRetriever;
import main.cayuse.exercise.service.imp.OpenWeatherMapWeatherRetriever;

public class Location {
	private static final Pattern ZIP_PATTERN = Pattern.compile("\\d{5}"); 
	private static final DecimalFormat ELEVATION_FORMAT = new DecimalFormat("#,###");

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must pass 1 argument: zip-code.");
		}
		String zip = args[0];
		validateZip(zip);
		Properties properties = readProperties();
		ExecutorService pool = Executors.newCachedThreadPool();
		ZipCodeDataRetreiver zipCodeDataRetreiver = getZipCodeDataRetreiver(
				properties.getProperty("openWeatherMapApiId"), properties.getProperty("googleApiKey"), pool);
		ZipCodeMetaData zipCodeMetaData = zipCodeDataRetreiver.getZipCodeMetaData(zip);
		printMetaData(zipCodeMetaData);
		pool.shutdown();
	}

	private static Properties readProperties() {
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

	private static void validateZip(String zip) {
		if (!ZIP_PATTERN.matcher(zip).matches()) {
			throw new IllegalArgumentException("Invalid zip-code [" + zip + "].");
			
		}
	}

	private static ZipCodeDataRetreiver getZipCodeDataRetreiver(String openWeatherMapApiId, String googleApiKey,
			ExecutorService pool) {
		Client client = ClientBuilder.newClient();
		WeatherRetriever weatherRetriever = new OpenWeatherMapWeatherRetriever(client, openWeatherMapApiId);
		TimeZoneRetriever timeZoneRetriever = new GoogleTimeZoneRetriever(client, googleApiKey);
		ElevationRetriever elevationRetriever = new GoogleElevationRetriever(client, googleApiKey);

		return new ZipCodeDataRetreiver(weatherRetriever, timeZoneRetriever, elevationRetriever, pool);
	}

	private static void printMetaData(ZipCodeMetaData metaData) {
		System.out.format(getFormattedMetaData(metaData));
	}

	// Visible for testing.
	static String getFormattedMetaData(ZipCodeMetaData metaData) {
		return String.format(
				"At the location %s, the temperature is %1.0f*C, the timezone is %s, and the elevation is %s meters.%n",
				metaData.getCity(), metaData.getTemperature(), metaData.getTimeZone(),
				ELEVATION_FORMAT.format(metaData.getElevation()));
	}

}

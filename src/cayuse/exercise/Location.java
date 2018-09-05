package cayuse.exercise;

import cayuse.exercise.service.WeatherRetriever;
import cayuse.exercise.service.data.TemperatureUnit;
import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.imp.OpenWeatherMapWeatherRetriever;
import cayuse.exercise.service.imp.transformers.ResponsesToZipCodeMetaData;

public class Location {

	public static void main(String[] args) {
		int zip = getValidatedZip(args);

		WeatherRetriever weatherRetriever = new OpenWeatherMapWeatherRetriever("d16f2baab3c871115936e2d6cca61968");

		WeatherData weatherData = weatherRetriever.getWeatherData(zip, TemperatureUnit.IMPERIAL);
		
		printMetaData(ResponsesToZipCodeMetaData.transform(weatherData));

//		metaData = retriever.getZipCodeMetaData(61025, TemperatureUnit.IMPERIAL);
//		printMetaData(metaData);
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

	public static void printMetaData(ZipCodeMetaData metaData) {
		System.out.format(getFormattedMetaData(metaData));
		System.out.println(metaData.getCity());
		System.out.println(metaData.getTemperature());
		System.out.println(metaData.getLatitude());
		System.out.println(metaData.getLongitude());
	}

	public static String getFormattedMetaData(ZipCodeMetaData metaData) {
		return String.format(
				"At the location %s, the temperature is %1.0f, the timezone is $TIMEZONE, and the elevation is $ELEVATION %n",
				metaData.getCity(), metaData.getTemperature());
	}

}

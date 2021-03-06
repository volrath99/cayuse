package cayuse.exercise.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;
import cayuse.exercise.service.transformer.ResponsesToZipCodeMetaData;

@Service
public class ZipCodeDataRetreiver {
	private static final Pattern ZIP_PATTERN = Pattern.compile("\\d{5}");

	private final WeatherRetriever weatherRetriever;
	private final TimeZoneRetriever timeZoneRetriever;
	private final ElevationRetriever elevationRetriever;

	public ZipCodeDataRetreiver(@Autowired WeatherRetriever weatherRetriever,
			@Autowired TimeZoneRetriever timeZoneRetriever, @Autowired ElevationRetriever elevationRetriever) {
		this.weatherRetriever = weatherRetriever;
		this.timeZoneRetriever = timeZoneRetriever;
		this.elevationRetriever = elevationRetriever;
	}

	public ZipCodeMetaData getZipCodeMetaData(String zipCode) throws InterruptedException, ExecutionException {
		
		if (!ZIP_PATTERN.matcher(zipCode).matches()) {
			throw new IllegalArgumentException("Invalid zip-code [" + zipCode + "].");
		}
		
		WeatherData weatherData = weatherRetriever.getWeatherData(zipCode);

		Future<String> timeZoneFuture = timeZoneRetriever.getTimeZone(weatherData.getLatitude(),
				weatherData.getLongitude());
		Future<Double> elevationFuture = elevationRetriever.getElevation(weatherData.getLatitude(),
				weatherData.getLongitude());

		String timeZone = timeZoneFuture.get();
		double elevation = elevationFuture.get();

		return ResponsesToZipCodeMetaData.transform(weatherData, timeZone, elevation);
	}

}

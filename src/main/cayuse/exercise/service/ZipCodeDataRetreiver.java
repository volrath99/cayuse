package main.cayuse.exercise.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import main.cayuse.exercise.service.data.ResponsesToZipCodeMetaData;
import main.cayuse.exercise.service.data.WeatherData;
import main.cayuse.exercise.service.data.ZipCodeMetaData;

public class ZipCodeDataRetreiver {
	private final WeatherRetriever weatherRetriever;
	private final TimeZoneRetriever timeZoneRetriever;
	private final ElevationRetriever elevationRetriever;
	private final LoadingCache<String, ZipCodeMetaData> cachedZipCodeMetaDatas;
	private final ExecutorService pool;

	public ZipCodeDataRetreiver(WeatherRetriever weatherRetriever, TimeZoneRetriever timeZoneRetriever,
			ElevationRetriever elevationRetriever, ExecutorService pool) {
		this.weatherRetriever = weatherRetriever;
		this.timeZoneRetriever = timeZoneRetriever;
		this.elevationRetriever = elevationRetriever;
		this.pool = pool;
		cachedZipCodeMetaDatas = CacheBuilder.newBuilder().maximumSize(500).expireAfterWrite(1, TimeUnit.MINUTES)
				.build(new CacheLoader<String, ZipCodeMetaData>() {
					@Override
					public ZipCodeMetaData load(String zipCode) throws Exception {
						return getUnCachedZipCodeMetaData(zipCode);
					}
				});
	}

	public ZipCodeMetaData getZipCodeMetaData(String zipCode) throws InterruptedException, ExecutionException {
		return cachedZipCodeMetaDatas.get(zipCode);
	}

	// TODO: Add logs to obtain frequency and API call timing.
	private ZipCodeMetaData getUnCachedZipCodeMetaData(String zipCode) throws InterruptedException, ExecutionException {
		WeatherData weatherData = weatherRetriever.getWeatherData(zipCode);

		// TODO: Add a separate cache for timezone and elevation for a longer period as they don't change.
		// Async.
		Future<String> timeZoneFuture = pool.submit(() -> {
			return timeZoneRetriever.getTimeZone(weatherData.getLatitude(), weatherData.getLongitude());
		});
		Future<Double> elevationFuture = pool.submit(() -> {
			return elevationRetriever.getElevation(weatherData.getLatitude(), weatherData.getLongitude());
		});
		String timeZone = timeZoneFuture.get();
		double elevation = elevationFuture.get();

		return ResponsesToZipCodeMetaData.transform(weatherData, timeZone, elevation);
	}

}

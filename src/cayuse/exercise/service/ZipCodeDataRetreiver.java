package cayuse.exercise.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import cayuse.exercise.service.data.ResponsesToZipCodeMetaData;
import cayuse.exercise.service.data.WeatherData;
import cayuse.exercise.service.data.ZipCodeMetaData;

public class ZipCodeDataRetreiver {
	private final WeatherRetriever weatherRetriever;
	private final TimeZoneRetriever timeZoneRetriever;
	private final ElevationRetriever elevationRetriever;
	private final ExecutorService timeZoneExecutor;
	private final ExecutorService elevationExecutor;
	private final LoadingCache<Integer, ZipCodeMetaData> cachedZipCodeMetaDatas;

	public ZipCodeDataRetreiver(WeatherRetriever weatherRetriever, TimeZoneRetriever timeZoneRetriever,
			ElevationRetriever elevationRetriever) {
		this.weatherRetriever = weatherRetriever;
		this.timeZoneRetriever = timeZoneRetriever;
		this.elevationRetriever = elevationRetriever;
		timeZoneExecutor = Executors.newFixedThreadPool(4);
		elevationExecutor = Executors.newFixedThreadPool(4);
		cachedZipCodeMetaDatas = CacheBuilder.newBuilder().maximumSize(500).expireAfterWrite(1, TimeUnit.MINUTES)
				.build(new CacheLoader<Integer, ZipCodeMetaData>() {
					@Override
					public ZipCodeMetaData load(Integer zipCode) throws Exception {
						return getUnCachedZipCodeMetaData(zipCode);
					}
				});
	}

	public ZipCodeMetaData getZipCodeMetaData(int zipCode) throws InterruptedException, ExecutionException {
		return cachedZipCodeMetaDatas.get(zipCode);
	}

	// TODO: Add logs to obtain frequency and API call timing.
	private ZipCodeMetaData getUnCachedZipCodeMetaData(int zipCode) throws InterruptedException, ExecutionException {
		WeatherData weatherData = weatherRetriever.getWeatherData(zipCode);

		// Async.
		Future<String> timeZoneFuture = timeZoneExecutor.submit(() -> {
			return timeZoneRetriever.getTimeZone(weatherData.getLatitude(), weatherData.getLongitude());
		});
		Future<Double> elevationFuture = elevationExecutor.submit(() -> {
			return elevationRetriever.getElevation(weatherData.getLatitude(), weatherData.getLongitude());
		});
		String timeZone = timeZoneFuture.get();
		double elevation = elevationFuture.get();

		return ResponsesToZipCodeMetaData.transform(weatherData, timeZone, elevation);
	}

}

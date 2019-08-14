package cayuse.exercise.service;

import java.util.concurrent.CompletableFuture;

public interface TimeZoneRetriever {
	CompletableFuture<String> getTimeZone(double latitude, double longitude);
}

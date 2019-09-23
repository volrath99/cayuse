package cayuse.exercise.service;

import java.util.concurrent.CompletableFuture;

public interface ElevationRetriever {
	
	/**
	 * Returns elevation in meters. 
	 */
	CompletableFuture<Double> getElevation(double latitude, double longitude);
}

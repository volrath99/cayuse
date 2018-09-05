package cayuse.exercise.service;

public interface ElevationRetriever {
	
	/**
	 * Returns elevation in meters. 
	 */
	double getElevation(double latitude, double longitude);
}

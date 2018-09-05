package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoogleElevationResults {
	private double elevation;
	@JsonIgnore
	private Object location;
	@JsonIgnore
	private double resolution;

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public void setLocation(Object location) {
		this.location = location;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}
}

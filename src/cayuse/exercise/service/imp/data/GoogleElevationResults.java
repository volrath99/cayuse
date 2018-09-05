package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoogleElevationResults {
	public double elevation;
	@JsonIgnore
	public Object location;
	@JsonIgnore
	public double resolution;

	public double getElevation() {
		return elevation;
	}
}

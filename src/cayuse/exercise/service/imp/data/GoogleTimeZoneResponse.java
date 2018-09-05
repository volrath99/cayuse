package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoogleTimeZoneResponse {
	@JsonIgnore
	public int dstOffset;
	@JsonIgnore
	public int rawOffset;
	@JsonIgnore
	public String status;
	@JsonIgnore
	public String timeZoneId;
	public String timeZoneName;

	public String getTimeZoneName() {
		return timeZoneName;
	}

}

package cayuse.exercise.service.imp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GoogleTimeZoneResponse {
	@JsonIgnore
	private int dstOffset;
	@JsonIgnore
	private int rawOffset;
	private String status;
	@JsonIgnore
	private String timeZoneId;
	private String timeZoneName;
	private String errorMessage;

	public String getTimeZoneName() {
		return timeZoneName;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setDstOffset(int dstOffset) {
		this.dstOffset = dstOffset;
	}

	public void setRawOffset(int rawOffset) {
		this.rawOffset = rawOffset;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

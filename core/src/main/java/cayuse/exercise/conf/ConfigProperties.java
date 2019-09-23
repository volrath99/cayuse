package cayuse.exercise.conf;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "cayuse")
@Validated
public class ConfigProperties {

	private final OpenWeatherMapApi openWeatherMapApi = new OpenWeatherMapApi();

	private final GoogleApi googleApi = new GoogleApi();

	public OpenWeatherMapApi getOpenWeatherMapApi() {
		return openWeatherMapApi;
	}

	public GoogleApi getGoogleApi() {
		return googleApi;
	}

	public static class OpenWeatherMapApi {

		@NotEmpty
		private String url;

		@NotEmpty
		private String apiId;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getApiId() {
			return apiId;
		}

		public void setApiId(String apiId) {
			this.apiId = apiId;
		}

	}

	public static class GoogleApi {

		@NotEmpty
		private String url;

		@NotEmpty
		private String apiKey;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

	}

}

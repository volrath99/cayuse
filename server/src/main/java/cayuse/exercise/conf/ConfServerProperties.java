package cayuse.exercise.conf;

import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "cayuse.server")
@Validated
public class ConfServerProperties {

	@Min(1)
	private int cacheInMinutes;

	@Min(1)
	private int cacheMax;

	@Min(1)
	private int threads;

	public int getCacheInMinutes() {
		return cacheInMinutes;
	}

	public void setCacheInMinutes(int cacheInMinutes) {
		this.cacheInMinutes = cacheInMinutes;
	}

	public int getCacheMax() {
		return cacheMax;
	}

	public void setCacheMax(int cacheMax) {
		this.cacheMax = cacheMax;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

}

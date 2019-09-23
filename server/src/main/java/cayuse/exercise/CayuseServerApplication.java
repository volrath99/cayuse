package cayuse.exercise;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import cayuse.exercise.conf.ConfServerProperties;
import cayuse.exercise.service.data.ZipCodeMetaData;

@EnableAsync
@SpringBootApplication
public class CayuseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CayuseServerApplication.class, args);
	}

	@Bean
	public Executor taskExecutor(ConfServerProperties confServerProperties) {
		return Executors.newFixedThreadPool(confServerProperties.getThreads());
	}

	@Bean
	public Client getClient() {
		return ClientBuilder.newClient();
	}

	@Bean
	public Cache<String, ZipCodeMetaData> getCache(ConfServerProperties confServerProperties) {
		return CacheBuilder.newBuilder().expireAfterWrite(confServerProperties.getCacheInMinutes(), TimeUnit.MINUTES)
				.maximumSize(confServerProperties.getCacheMax()).build();
	}

}

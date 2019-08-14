package cayuse.exercise;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.data.ZipCodeMetaData;

@SpringBootApplication
@EnableAsync
public class CayuseApplication implements ApplicationRunner {

	private static final Pattern ZIP_PATTERN = Pattern.compile("\\d{5}");
	private static final DecimalFormat ELEVATION_FORMAT = new DecimalFormat("#,###");

	@Autowired
	private ZipCodeDataRetreiver zipCodeDataRetreiver;

	public static void main(String[] args) {
		SpringApplication.run(CayuseApplication.class, args).close();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> nonOptionalArgs = args.getNonOptionArgs();
		if (nonOptionalArgs.size() < 1) {
			throw new IllegalArgumentException("You must pass 1 argument: zip-code.");
		}
		String zipCode = nonOptionalArgs.get(0);
		validateZip(zipCode);
		ZipCodeMetaData zipCodeMetaData = zipCodeDataRetreiver.getZipCodeMetaData(zipCode);
		printMetaData(zipCodeMetaData);
	}

	@Bean
	public Executor taskExecutor() {
		return Executors.newFixedThreadPool(2);
	}

	@Bean
	public Client getClient() {
		return ClientBuilder.newClient();
	}

	private static void validateZip(String zip) {
		if (!ZIP_PATTERN.matcher(zip).matches()) {
			throw new IllegalArgumentException("Invalid zip-code [" + zip + "].");
		}
	}

	private static void printMetaData(ZipCodeMetaData metaData) {
		System.out.format(getFormattedMetaData(metaData));
	}

	// Visible for testing.
	static String getFormattedMetaData(ZipCodeMetaData metaData) {
		return String.format(
				"At the location %s, the temperature is %1.0f*C, the timezone is %s, and the elevation is %s meters.%n",
				metaData.getCity(), metaData.getTemperature(), metaData.getTimeZone(),
				ELEVATION_FORMAT.format(metaData.getElevation()));
	}
}

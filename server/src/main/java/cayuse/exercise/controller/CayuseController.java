package cayuse.exercise.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.Cache;

import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.data.ZipCodeMetaData;

@RestController
@RequestMapping("cayuse")
public class CayuseController {

	@Autowired
	private Cache<String, ZipCodeMetaData> cache;

	@Autowired
	private ZipCodeDataRetreiver zipCodeDataRetreiver;

	@CrossOrigin
	@GetMapping(path = "/location/{zipCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ZipCodeMetaData getLocationInfo(@PathVariable String zipCode)
			throws InterruptedException, ExecutionException {
		return cache.get(zipCode, () -> {
			return zipCodeDataRetreiver.getZipCodeMetaData(zipCode);
		});
	}

}

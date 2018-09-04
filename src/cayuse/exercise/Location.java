package cayuse.exercise;

import cayuse.exercise.service.ZipCodeDataRetreiver;
import cayuse.exercise.service.imp.OpenWeatherMapZipCodeDataRetreiver;

public class Location {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must pass 1 argument: zip-code.");
		}
		
		// TODO: validate zip code.
		int zip = Integer.valueOf(args[0]);
		System.out.println(zip);
		
		ZipCodeDataRetreiver retriever = new OpenWeatherMapZipCodeDataRetreiver();
		retriever.getZipCodeMetaData(zip);
		retriever.getZipCodeMetaData(61025);
		
		// TODO: TRY CALLING several times with different zip codes.
	}

}

# cayuse

Given a zip returns the City Name, Temperature, Time-Zone, and Elevation.

* Add a resources folder, create cayuse.properties, and add the properties for [openWeatherMapApiId](https://home.openweathermap.org/api_keys) and [googleApiKey](https://developers.google.com/maps/documentation/timezone/get-api-key) with your keys.
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/install.html)
* mvn package
* java -jar target/Cayuse-0.0.1-SNAPSHOT.jar <ZIP_CODE> 

# Cayuse Exercise

Given a zip returns the City Name, Temperature, Time-Zone, and Elevation.

* Update cayuse.properties with your [openWeatherMapApiId](https://home.openweathermap.org/api_keys) and [googleApiKey](https://developers.google.com/maps/documentation/timezone/get-api-key) with your keys.
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/install.html)
* mvn clean package
* java -jar -Dspring.profiles.active=dev target/Cayuse.jar <ZIP_CODE> 

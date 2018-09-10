# cayuse

Given a zip returns the City Name, Temperature, Time-Zone, and Elevation.

* Add a resources folder, create cayuse.properties, and add the properties for [openWeatherMapApiId](https://home.openweathermap.org/api_keys) and [googleApiKey](https://developers.google.com/maps/documentation/timezone/get-api-key) with your keys.
* Install [ANT](https://ant.apache.org/bindownload.cgi)
* Add [ivy.jar](http://ant.apache.org/ivy/download.cgi) to the ant lib directory
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Run ant
* Run java -jar Location.jar <ZIP_CODE> 

# Cayuse Exercise

Given a zip returns the City Name, Temperature, Time-Zone, and Elevation.

* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/install.html)
* [Get a Open Weather Map API ID](https://home.openweathermap.org/api_keys)
* [Get a Google API Key](https://developers.google.com/maps/documentation/timezone/get-api-key)
* Create application-dev.yml under src/main/resources withthe following:
  ```
  cayuse:
    open-weather-map-api:
      api-id: <openWeatherMapApiId>
    google-api:
      api-key: <googleApiKey>
  ```
* mvn clean package
* java -jar -Dspring.profiles.active=dev target/Cayuse.jar <ZIP_CODE> 

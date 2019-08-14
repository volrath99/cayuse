# Cayuse Exercise

Given a zip returns the City Name, Temperature, Time-Zone, and Elevation.

## Setup
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/install.html)
* [Get a Open Weather Map API ID](https://home.openweathermap.org/api_keys)
* [Get a Google API Key](https://developers.google.com/maps/documentation/timezone/get-api-key)
* Create application-dev.yml under src/main/resources with the following:
  ```
  cayuse:
    open-weather-map-api:
      api-id: <openWeatherMapApiId>
    google-api:
      api-key: <googleApiKey>
  ```

## Run
* mvn clean spring-boot:run -Dspring-boot.run.arguments=<ZIP_CODE> -Dspring.profiles.active=dev

or

* mvn clean package
* java -jar -Dspring.profiles.active=dev target/Cayuse.jar <ZIP_CODE> 

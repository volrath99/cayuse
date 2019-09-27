# Cayuse Exercise
  
Given a zip returns the City Name, Temperature, Time-Zone, and Elevation. Run from the command line tool or start the web server.

_core_ - library containing all the shared logic

_tool_ - command line tool

_server_ - web server


## Setup
* Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Maven](https://maven.apache.org/install.html)
* [Get a Open Weather Map API ID](https://home.openweathermap.org/api_keys)
* [Get a Google API Key](https://developers.google.com/maps/documentation/timezone/get-api-key)
* Create application-dev.yml under core/src/main/resources with the following:
  ```
  cayuse:
    open-weather-map-api:
      api-id: <openWeatherMapApiId>
    google-api:
      api-key: <googleApiKey>
  ```

## Run Command Line Tool
* mvn clean install -Dspring.profiles.active=dev
* mvn spring-boot:run -Dspring.profiles.active=dev  -Dspring-boot.run.arguments=<ZIP_CODE> -pl tool

or

* mvn clean package -Dspring.profiles.active=dev
* java -Dspring.profiles.active=dev -jar tool/target/Cayuse-tool.jar <ZIP_CODE>

## Web Server
* mvn clean install -Dspring.profiles.active=dev
* mvn spring-boot:run -Dspring.profiles.active=dev -pl server

or

* mvn clean package -Dspring.profiles.active=dev
* java -Dspring.profiles.active=dev -jar server/target/Cayuse-server.jar

### REST Calls

GET /cayuse/location/<ZIP_CODE>
```json
{
  "city": "City Name",
  "temperature": 13.92,
  "latitude": 45.48,
  "longitude": -122.56,
  "timeZone": "Pacific Standard Time",
  "elevation": 63.40010070800781
}
```

### Example
[React UI Example](https://github.com/volrath99/cayuse-front-end)

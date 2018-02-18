# Sainsbury's Scraper

Scraper is a small command line/console utility. It is used for retrieving information 
from the Sainsbury's website about products and displaying the information to the user in the 
console output.

## Dependencies  

Scraper utility assumes the following dependencies installed on the system.

- Java 1.8 JDK

## Build/Test

Scraper uses Gradle to control its build and test process. It follows the standard tasks provided
by many Gradle projects such as:

Build the JAR. Scraper utilises the [ShadowJar](http://imperceptiblethoughts.com/shadow/) plugin to build 
a "fat" JAR containing all the dependencies required to run the application.

```
$ ./gradlew clean shadowJar
```

Run tests
```
$ ./gradlew clean check
```

## Run

Execute the application 
```
java -jar build/libs/scraper-1.0-all.jar
```

Scrape a different url
```
java -jar build/libs/scraper-1.0-all.jar --url http://some-url.com
```

View all parameters
```
java -jar build/libs/scraper-1.0-all.jar --help
```

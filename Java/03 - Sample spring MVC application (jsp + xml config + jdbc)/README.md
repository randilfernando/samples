# Spring MVC Application (jsp + xml config + jdbc)
Sample spring MVC application using xml config and jsp
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Open "Java/02 - Sample spring MVC application (jsp + xml config + jdbc)"    
Open command prompt  
## Run server
If maven is not installed please install maven.  
This project contains embedded jetty server.
```bash
mvn jetty:run
```
Or you can create the war file using ```mvn clean install``` then deploy it in the server (eg: apache tomcat)  
Go to url [localhost:8080/hello?name=World](localhost:8080/hello?name=World)
# Concepts covered
- XML config (web.xml, appconfig-root.xml)
- beans
- annotation based configuration
- Services
- Autowiring (Dependency injection)
- Controllers (Request mapping)

>Note: Go to docs folder to follow extra exercises :)


# Servlet Application (xml config + jsp)
Sample java servlet application
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Go to "Java/02 - Sample java servlet application (pure servlet)"  
Open terminal  
## Run server
If maven is not installed please install maven.  
This project contains embedded jetty server.
```bash
mvn jetty:run
```
Or you can create the war file using ```mvn clean install``` then deploy it in the server (eg: apache tomcat)  
Go to [localhost:8080/login](http://localhost:8080/login) to access login end point.




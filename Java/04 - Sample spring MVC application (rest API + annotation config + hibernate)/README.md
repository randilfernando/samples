# Spring MVC (Rest API)
Sample spring MVC application which implemented with mysql support

# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Open "Java/04 - Sample spring MVC application (rest API + annotation config + hibernate)" folder  
Open command prompt
## Configure database
1. Create new mysql database
2. Configure database properties and credentials in application.properties file
```yml
db.url=jdbc:mysql://localhost:3306/<database>
db.username=<username>
db.password=<password>
```
## Run server
If maven is not installed please install maven.  
This project contains embedded jetty server.
```bash
mvn jetty:run
```
Or you can create the war file using ```mvn clean install``` then deploy it in the server (eg: apache tomcat)
## Use api
### End points available
1. /user - Get all users details
2. /user/{id} - Get details of a specific user (using id)
3. /user - Post request will add new user

>Note: All the requests must use JSON data type
# SpringBoot Application (Rest API + mysql)
Sample spring boot application which implemented with mysql support

# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Open "Java/05 - Sample spring boot application(rest API + hibernate)" folder  
Open command prompt
## Configure database
1. Create new mysql database
2. Configure database properties and credentials in application.properties file
```yml
spring.datasource.url=jdbc:mysql://localhost:3306/<database>?autoReconnect=true&useSSL=false
spring.datasource.username=<username>
spring.datasource.password=<password>
```
## Run server
```bash
mvn spring-boot:run
```
## Use api
## End points available
1. /user - Get all users details
2. /user/{id} - Get details of a specific user (using id)
3. /user - Post request will add new user

>Note: All the requests must use JSON data type
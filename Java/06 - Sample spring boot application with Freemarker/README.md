# SpringBoot Application with Apache freemarker
Sample spring boot application with freemarker template engine
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Open "Java/06 - Sample spring boot application with Freemarker" folder  
Open command prompt
## Create database
1. Create mysql database
2. Insert database credentials (resources/application.properties)
```yml
spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>?autoReconnect=true&useSSL=false
spring.datasource.username=<username>
spring.datasource.password=<password>
```
## Run server
```bash
mvn spring-boot:run
```
## Use html end points
1. /view-all-samples - Render template with all samples data
2. /view-single-sample?id={id} - Render template with specific sample data (with id) add id as a query parameter
3. /add-new-sample - Render add new sample page
4. /view-samples-with-jquery - Render page which uses jquery to get data via api end points
## Use API end points
1. /api/samples - Get all samples
2. /api/samples/{id} - Get specific sample (using id)
3. /api/samples - Add new sample
>Note: All the requests must use JSON data type
# Further reading
1. [Freemarker directives available](https://freemarker.apache.org/docs/ref_directives.html)
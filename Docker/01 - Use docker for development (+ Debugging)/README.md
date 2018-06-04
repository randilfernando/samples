# Use docker for development
Sample dockerized spring boot application
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Open "Docker/01 - Use docker for development (+Debugging)" folder  
Open command prompt
## Create and run docker container
```bash
mvn package dockerfile:build
docker run --rm -it -p 8080:8080 alternate/docker-sample
```
>Note: Maven docker plugin used to ease the development process
## Test docker container
Go to [http://localhost:8080/greet?name=world](http://localhost:8080/greet?name=world)
# Extra
#### Difference between EXPOSE and -p (publish)
1. Neither specify EXPOSE not -p: Service can only access within the container
1. EXPOSE: Service can access from other containers
1. -p (publish): Service can access from anywhere (eg: from localhost)

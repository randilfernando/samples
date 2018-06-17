# Microservice with spring web-sockets support with redis message broker
Microservice with spring web-sockets application bridge clients connect with multiple servers via redis message broker

# How to setup

## Install additional tools
- Install [maven](https://maven.apache.org/download.cgi)
- Install [node + npm](https://nodejs.org/en/)
- Install [redis](https://redis.io/)
>Note: You only need to install redis if you are not using docker-compose to start application

## Clone and build project
- Clone samples repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "MicroServices/03 - Microservices with spring web-sockets + Redis message broker" folder
- Install dependencies and build projects
```
mvn package
```

# Start application

## Using docker
```
docker-compose up
```
>Note: You need to install docker and docker-compose

## Without using docker
- Start websocket-server
```
java -jar websocket-server/target/websocket-server-0.0.1-SNAPSHOT.jar
```
- Start redis-publisher
```
java -jar redis-publisher/target/redis-publisher-0.0.1-SNAPSHOT.jar
```
- Serve angular client
```
cd websocket-client
npm run serve
```

# Test service
Goto [http://localhost:4200](http://localhost:4200)
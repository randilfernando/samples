# Dockerfile

FROM openjdk:8-jre-alpine

MAINTAINER  randilfernando <randil.fernando@outlook.com>

ARG JAR_FILE

VOLUME /tmp

# Run java application

COPY ${JAR_FILE} /usr/src/demo/app.jar

WORKDIR /usr/src/demo

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]
# Dockerfile

FROM openjdk:8-jre-alpine

MAINTAINER  randilfernando <randil.fernando@outlook.com>

VOLUME /tmp

COPY ./target/redis-publisher-0.0.1-SNAPSHOT.jar /usr/src/demo/app.jar

WORKDIR /usr/src/demo

ENV REDIS_HOST redis

ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]
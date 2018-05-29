# Microservices with Spring cloud streams and confluent
Samples micro services with spring cloud kafka streaming binder with confluent avro serializer.

# How to setup

## Install and configure third party applications

### Confluent
- Download [confluent](https://www.confluent.io/download/)
- Go to confluent installed location
- Start confluent platform
```
bin/confluent start
```
- Create "order-topic" which id required by the application to send data
```
bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-topic
```

## Clone and build project
- Clone samples repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "MicroServices/02 - MIcroservices with Spring cloud streams and Confluent (kafka + avro)" folder
- Run micro services (Run both projects)
```
cd customer-service
mvn spring-boot:run
```
```
cd order-service
mvn spring-boot:run
```

# Test service
1. Send request to order-service to create new order (http://localhost:4001/orders)
```json
{
  "id": "CR-0001",
  "customerId": "M-0001",
  "amount": 1000.00,
  "status": true
}
```
2. Order service will serialize order objct into avro stream and send
3. Customer service will listen stream and then deserialize avro stream into order object
4. See: Console output of customer-service

>Note: All the requests must use JSON data type

# Why com.alternate.schemas.Order is missing?
com.alternate.schemas.Order is an auto generated class using avro schema hence Order class is not version controlled.
To generate Order class go to each service and run `mvn generate-sources`
```
cd customer-service
mvn generate-sources
```
```
cd order-service
mvn generate-sources
```
And add target/generated-sources/avro as a source root 
(Eg: In intelij idea we can easily do this by right click on targer/generated-sources folder => Mark Directory As => Sources root)

>Note: This step only needed for the development purposed only

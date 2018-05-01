# Microservices with eventual consistency
Samples micro services with spring boot and kafka which use eventual consistency.

# How to compile

## Install and run third party applications

### Install and configure mysql
- Install mysql database
- Create databases for micro services (order_service and customer_service)
>Note: see application.properties files
- Add username and password to application.properties files

### Install and configure apache kafka
- Download [apache kafka](https://kafka.apache.org/downloads)
- Go to kafka installed location
- Start inbuilt zookeeper
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
- Start kafka
```
bin/kafka-server-start.sh config/server.properties
```
- Create topics (order and customer topics)
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic customer
```

## Clone and build project
- Clone samples repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "MicroServices/01 - Microservices with eventual consistency (Spring boot + kafka)" folder
- Run micro services (go to each project and then run)
```
mvn spring-boot:run
```

## Use api
## End points available
- /customers - Add new customer
```json
{
  "customerName": "test",
  "creditLimit": 10000
}
```
- /customers/{id} - Get customer using id
- /orders - Add new order
```json
{
  "customerId": 1,
  "orderTotal": 2000
}
```
- /orders/{id} - Get order by id

>Note: All the requests must use JSON data type
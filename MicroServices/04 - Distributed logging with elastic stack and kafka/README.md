# Distributed logging with elastic stack and kafka
Stream logs from multiple servers via kafka stream and enable monitoring services to consume them.

# How to setup

## Install and configure third party applications

### Elastic stack (Elasticsearch, Logstash, Kibana)
- Download [elasticsearch](https://www.elastic.co/downloads/elasticsearch)
- Download [logstash](https://www.elastic.co/downloads/logstash)
- Download [kibana](https://www.elastic.co/downloads/kibana)
- Configure elasticsearch, logstash and kibana configurations
- Start elasticsearch and kibana
- Start logstash with following configuration file
```conf
input {
    kafka {
        bootstrap_servers => "localhost:9092"
        topics => ["sample-log"]
	codec => json {
	    charset => "UTF-8"
        }
    }
}

output {
    elasticsearch {
        hosts => ["localhost:9200"]
        index => "sample-log"
        workers => 1
    }
}
```

### Apache Kafka
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
- Add kafka server host and port to log4j.properties file
```yml
log4j.appender.kafka.brokerList=localhost:9092

```

## Build project
- Clone samples repository
```
git clone https://github.com/randilfernando/samples.git
```
- Go to "MicroServices/04 - Distributed logging with elastic stack and kafka" folder
- Build and run application
```
mvn clean package
java -jar target/sample-1.0-SNAPSHOT.jar
```

# Use application
- After application started it will ask for messages to log
- Type `exit` to close the application

# View logs
- Goto kibana user interface [localhost:5601](http://localhost:5601)
- Goto Management -> Index patterns -> Create index pattern
- Create index pattern as `sample-log`
- Goto discover and view logs saved under `sample-log` index

>Note: logs are stored in json format and parse them in logstash before visualization
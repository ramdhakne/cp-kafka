# cp-kafka
Repo for cp-kafka.

MQTT source produces data into server https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions

We subscribe to it and write data into Kafka Topic called 'vehicle-positions'. Data is coming from Helsinki Regional Transport Authority(HSL). All vehicles publish their data to MQTT server per second. Every MQTT message has two parts, the topic and the binary payload. 

Java producer is dockerized, here is the sample command to use if authentication mechanism is PLAIN

`docker container run --network=host --rm -e KAFKA_BROKERS=<KAFKA_BROKER_BOOTSTRAP_URL> -e KAFKA_USERNAME=<USERNAME> -e KAFKA_PASSWORD=<PASSWORD> -it proton69/java-producer:paramsV2`

  
eg:

`docker container run --network=host --rm -e KAFKA_BROKERS=ip-10-9-8-13.us-west-2.compute.internal:9092 -e KAFKA_USERNAME=test -e KAFKA_PASSWORD=test123 -it proton69/java-producer:paramsV2`

# cp-kafka
Repo for cp-kafka.

MQTT source produces data into server https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions

We subscribe to it and write data into Kafka Topic called 'vehicle-positions'. Data is coming from Helsinki Regional Transport Authority(HSL). All vehicles publish their data to MQTT server per second. Every MQTT message has two parts, the topic and the binary payload. 

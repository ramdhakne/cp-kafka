package clients;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.paho.client.mqttv3.*;

public class VehiclePositionProducer {
    public static void main(String[] args) throws MqttException {
        String brokers = System.getenv("KAFKA_BROKERS");
		String username = System.getenv("KAFKA_USERNAME");
        String password = System.getenv("KAFKA_PASSWORD");
        
        System.out.printf("brokers [%s]\n", brokers);
        System.out.printf("username [%s]\n", username);
        System.out.printf("password [%s]\n", password);
        System.out.println("*** Starting VP Producer ***");

        String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);

        Properties settings = new Properties();
        settings.put(ProducerConfig.CLIENT_ID_CONFIG, "vp-producer");
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        //System.out.printf("My broker URL [%s]\n", ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
        settings.put("sasl.mechanism", "PLAIN");
        //settings.put("sasl.mechanism", "PLAIN");                // for gke broker
        //settings.put("security.protocol", "SASL_PLAINTEXT");    // for gke broker
        //settings.put("sasl.jaas.config", jaasCfg);              // for gke broker
       
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        final KafkaProducer<String, String> producer = new KafkaProducer<>(settings);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("### Stopping VP Producer ###");
            producer.close();
        }));

        Subscriber subscriber = new Subscriber(producer);
        subscriber.start();
    }
}

package br.com.broker.kafka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class KafkaConsumerExecutor implements Runnable {

	private KafkaConsumer<String,String> consumer;
	
	public KafkaConsumerExecutor(String hostAndPort, String topic) {
		Properties consumerProperties = createProperties(hostAndPort);
		consumer = new KafkaConsumer<String, String>(consumerProperties);
		//consumer.assign(asList(new TopicPartition("mytopic", 0)));
        consumer.subscribe(Arrays.asList(topic));
	}
	
	@Override
	public void run() {
		while(true) {
            Map<TopicPartition, OffsetAndMetadata> committedOffsets = new HashMap<>();
            ConsumerRecords<String,String> records = consumer.poll(1000);
            records.forEach(msg -> {
            	System.out.println("Notify: " + msg.value());
                committedOffsets.put(
                        new TopicPartition(msg.topic(), msg.partition()),
                        new OffsetAndMetadata(msg.offset()));
            });
            consumer.commitSync(committedOffsets);
        }
	}
	
	private Properties createProperties(String hostAndPort) {
		Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", hostAndPort);
        consumerProperties.put("group.id", "notifyConsumer");
        // Set some default deserializers for the consumed messages.
        // This is required because otherwise Kafka doesn't know what to do with the data.
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
	}
}
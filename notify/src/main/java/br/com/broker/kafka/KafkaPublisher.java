package br.com.broker.kafka;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import br.com.broker.Publisher;
import br.com.model.Notify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class KafkaPublisher implements Publisher {
	
	private static final long DEFAULT_TIMEOUT_IN_MS = 1000;
	
	private KafkaProducer<String, String> producer;
	private String topic;
	private Gson gson;
	
	public KafkaPublisher(String hostAndPort, String topic) {
		Properties producerProperties = createProperties(hostAndPort);
		producer = new KafkaProducer<String, String>(producerProperties);
		this.topic = topic;
		this.gson = new GsonBuilder().create();
	}
	
	
	// TODO: criar exceção para erro no future
	@Override
	public void publish(Notify notify) throws Exception {
		ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic,  gson.toJson(notify));
		Future<RecordMetadata> future = producer.send(msg);
		future.get(DEFAULT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
	}

	private Properties createProperties(String hostAndPort) {
		Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", hostAndPort);
        producerProperties.put("serializer.class", "kafka.serializer.StringEncoder");
        producerProperties.put("request.required.acks", "1");
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
	}
	
}

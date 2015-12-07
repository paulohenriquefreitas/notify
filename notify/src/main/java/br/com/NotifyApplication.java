package br.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.broker.Publisher;
import br.com.broker.kafka.KafkaConsumerExecutor;
import br.com.broker.kafka.KafkaPublisher;

@SpringBootApplication
public class NotifyApplication {

    private static final String KAFKA_TOPIC_SYS_PROP = "kafka.topic";
	private static final String KAFKA_SERVER_SYS_PROP = "kafka.server";

	public static void main(String[] args) {
        SpringApplication.run(NotifyApplication.class, args);
    }
	
    @Bean
    public Publisher setupKafkaProducer() {
    	String hostAndPort = System.getProperty(KAFKA_SERVER_SYS_PROP);
    	String topic = System.getProperty(KAFKA_TOPIC_SYS_PROP);
    	return new KafkaPublisher(hostAndPort, topic);
    }
    
    @Bean
    public String createKafkaConsumerExecutor() {
    	String hostAndPort = System.getProperty(KAFKA_SERVER_SYS_PROP);
    	String topic = System.getProperty(KAFKA_TOPIC_SYS_PROP);
    	new Thread(new KafkaConsumerExecutor(hostAndPort, topic)).start();
    	return "TODO";
    }
}
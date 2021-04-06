package com.github.dhiraj072.kafkatwitterdemo;

import com.github.dhiraj072.kafkatwitterdemo.kafka.TweetSerializer;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@SpringBootApplication
public class KafkaTwitterDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaTwitterDemoApplication.class, args);
	}

	@Bean
	public Map<String, Object> producerConfigs() {

		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, TweetSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TweetSerializer.class);
		return props;
	}

	@Bean
	public ProducerFactory<String, Tweet> producerFactory() {

		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, Tweet> kafkaTemplate() {

		return new KafkaTemplate<	>(producerFactory());
	}
}

package com.github.dhiraj072.kafkatwitterdemo;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.github.dhiraj072.kafkatwitterdemo.kafka.TweetConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(topics = "first_topic", ports = 9092, zookeeperPort = 2181, bootstrapServersProperty = "localhost:9092")
class KafkaTwitterDemoApplicationTests {

	@Autowired
	private TweetConsumer tweetConsumer;

	@Test
	void test() throws InterruptedException {

		Thread.sleep(1000);
		assertFalse(tweetConsumer.getReceived().isEmpty());
	}

}

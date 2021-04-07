package com.github.dhiraj072.kafkatwitterdemo.kafka;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TweetConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TweetConsumer.class);

    List<String> received = new ArrayList<>();

    @KafkaListener(id = "tweet_consumer", topics = "first_topic")
    public void listen(String data) {

        LOGGER.info("Received tweet {}", data);
        received.add(data);
    }

    public List<String> getReceived() {

        return received;
    }
}

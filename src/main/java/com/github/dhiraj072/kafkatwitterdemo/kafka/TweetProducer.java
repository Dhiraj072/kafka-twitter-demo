package com.github.dhiraj072.kafkatwitterdemo.kafka;

import com.github.redouane59.twitter.dto.tweet.Tweet;
import java.util.function.Consumer;
import javax.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * A Kafka tweet producer
 */
@Service
public class TweetProducer implements Consumer<Tweet> {

    @Resource
    private KafkaTemplate<String, String> producer;

    @Override
    public void accept(Tweet tweet) {

        producer.send("first_topic", tweet.getText());
    }
}

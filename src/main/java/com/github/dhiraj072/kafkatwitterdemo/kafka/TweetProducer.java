package com.github.dhiraj072.kafkatwitterdemo.kafka;

import com.github.redouane59.twitter.dto.tweet.Tweet;
import java.util.function.Consumer;
import javax.annotation.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * A Kafka tweet producer. This receives tweets as a {@link Tweet} {@link Consumer}
 * and produces kafka messages via autowired {@link KafkaTemplate}
 */
@Service
public class TweetProducer implements Consumer<Tweet> {

    @Resource
    private KafkaTemplate<String, Tweet> producer;

    @Override
    public void accept(Tweet tweet) {

        producer.send("first_topic", tweet);
    }
}

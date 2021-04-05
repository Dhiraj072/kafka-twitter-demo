package com.github.dhiraj072.kafkatwitterdemo.twitter;

import com.github.dhiraj072.kafkatwitterdemo.kafka.TweetProducer;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import com.github.scribejava.core.model.Response;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to receive tweets from Twitter API and send them to {@link TweetProducer}
 */
@Service
public class TwitterService {

    public static Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

    private TwitterClient client;
    private Consumer<Tweet> consumer;
    private Future<Response> startStreamResp;

    /**
     * {@link TweetProducer} bean autowires here as a consumer
     */
    @Autowired
    public TwitterService(Consumer<Tweet> consumer) {

        this.consumer = consumer;
    }

    @PostConstruct
    public void init() throws IOException {

        LOGGER.info("Loading Twitter Service");
        client = new TwitterClient(TwitterClient.OBJECT_MAPPER
            .readValue(new File("credentials.json"), TwitterCredentials.class));
        LOGGER.info("Starting Twitter sampled stream");
        startStreamResp = client.startSampledStream(consumer);
    }

    @PreDestroy
    public void cleanup() {

        LOGGER.info("Stopping Twitter sampled stream");
        client.stopFilteredStream(startStreamResp);
    }
}

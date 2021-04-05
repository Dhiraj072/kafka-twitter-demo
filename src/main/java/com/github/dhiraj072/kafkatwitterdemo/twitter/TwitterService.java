package com.github.dhiraj072.kafkatwitterdemo.twitter;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

    public static Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

    public TwitterService() {

    }

    @PostConstruct
    public void init() throws IOException {

        LOGGER.info("Loading Twitter Service");
        TwitterClient client = new TwitterClient(TwitterClient.OBJECT_MAPPER
            .readValue(new File("credentials.json"), TwitterCredentials.class));
        LOGGER.info("Got tweet {}", client.getTweet("1354143048159031302").getText());
    }
}

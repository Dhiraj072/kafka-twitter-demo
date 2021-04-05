package com.github.dhiraj072.kafkatwitterdemo.twitter;

import static org.junit.jupiter.api.Assertions.*;

import com.github.redouane59.twitter.dto.tweet.Tweet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TwitterServiceTest {

    private List<Tweet> tweetsReceived;
    private TwitterService twitterService;

    @BeforeEach
    public void init() throws IOException, InterruptedException {

        tweetsReceived = new ArrayList<>();
        twitterService = new TwitterService((tweet) -> tweetsReceived.add(tweet));
        twitterService.init();
        Thread.sleep(1000); // wait for some tweets to be received
    }

    @Test
    public void test() {

        assertFalse(tweetsReceived.isEmpty());
        Tweet t = tweetsReceived.get(0);
        assertFalse(t.getText().isEmpty());
    }

    @AfterEach
    public void cleanup() {

        twitterService.cleanup();
    }
}
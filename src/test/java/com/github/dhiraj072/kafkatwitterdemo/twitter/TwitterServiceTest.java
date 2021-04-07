package com.github.dhiraj072.kafkatwitterdemo.twitter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TwitterServiceTest {

    private TwitterService twitterService;
    private TwitterClient twitterClient;
    private Consumer<Tweet> consumer;

    @BeforeEach
    public void init() {

        twitterClient = mock(TwitterClient.class);
        consumer = mock(Consumer.class);
        twitterService = new TwitterService(twitterClient, consumer);
        twitterService.init();
    }

    @Test
    public void test() {

        verify(twitterClient, times(1)).startSampledStream(consumer);
    }

    @AfterEach
    public void cleanup() {

        twitterService.cleanup();
    }
}
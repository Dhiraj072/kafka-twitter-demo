package com.github.dhiraj072.kafkatwitterdemo.kafka;

import static org.junit.jupiter.api.Assertions.*;

import com.github.redouane59.twitter.dto.tweet.Attachments;
import com.github.redouane59.twitter.dto.tweet.ContextAnnotation;
import com.github.redouane59.twitter.dto.tweet.Geo;
import com.github.redouane59.twitter.dto.tweet.ReplySettings;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.tweet.TweetType;
import com.github.redouane59.twitter.dto.user.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TweetSerializerTest {

    /*
    A very minimal implementation of Tweet for test purposes
     */
    static class MockTweet implements Tweet {

        @Override
        public String getId() {

            return "id";
        }

        @Override
        public String getText() {

            return "text";
        }

        @Override
        public User getUser() {

            return null;
        }

        @Override
        public String getAuthorId() {

            return "authorId";
        }

        @Override
        public int getRetweetCount() {

            return 0;
        }

        @Override
        public int getLikeCount() {

            return 0;
        }

        @Override
        public int getReplyCount() {

            return 0;
        }

        @Override
        public int getQuoteCount() {

            return 0;
        }

        @Override
        public LocalDateTime getCreatedAt() {

            return null;
        }

        @Override
        public String getLang() {

            return "lang";
        }

        @Override
        public String getInReplyToUserId() {

            return null;
        }

        @Override
        public String getInReplyToStatusId() {

            return null;
        }

        @Override
        public String getInReplyToStatusId(TweetType type) {

            return null;
        }

        @Override
        public List<ContextAnnotation> getContextAnnotations() {

            return null;
        }

        @Override
        public TweetType getTweetType() {

            return null;
        }

        @Override
        public String getConversationId() {

            return null;
        }

        @Override
        public ReplySettings getReplySettings() {

            return null;
        }

        @Override
        public Geo getGeo() {

            return null;
        }

        @Override
        public Attachments getAttachments() {

            return null;
        }
    }

    @Test
    public void test() {

        byte[] serializedTweet = new TweetSerializer().serialize("test", new MockTweet());
        assertNotNull(serializedTweet);
    }
}
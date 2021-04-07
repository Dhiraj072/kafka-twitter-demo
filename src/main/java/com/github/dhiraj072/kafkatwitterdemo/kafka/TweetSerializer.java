package com.github.dhiraj072.kafkatwitterdemo.kafka;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.tweet.TweetV2.TweetData;
import com.github.redouane59.twitter.dto.user.User;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serialize a {@link Tweet}
 */
public class TweetSerializer implements Serializer<Tweet> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TweetSerializer.class);

    private static final SimpleModule SERIALIZE_EXCEPT_ERR_USER_FIELD =
        new SimpleModule().setSerializerModifier(new BeanSerializerModifier() {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
            List<BeanPropertyWriter> beanProperties) {

            return beanProperties.stream().map(bpw -> new BeanPropertyWriter(bpw) {

                @Override
                public void serializeAsField(Object bean, JsonGenerator gen,
                    SerializerProvider prov) throws Exception {
                    try {

                        super.serializeAsField(bean, gen, prov);
                    } catch (InvocationTargetException e) {

                        if (this.getName().equals("user"))
                            LOGGER.warn("Ignoring {} for field {} of {} instance", e.getClass().getName(),
                                this.getName(), bean.getClass().getName());
                        else
                            throw e;
                    }
                }
            }).collect(Collectors.toList());
        }
    });

    /**
     * {@link TweetData#getUser()} always throws {@link UnsupportedOperationException} which does not play well with
     * Jackson's {@link ObjectMapper#writeValueAsString(Object)}} inside {@link #serialize(String, Tweet)}.
     * So we register the custom Jackson module {@link #SERIALIZE_EXCEPT_ERR_USER_FIELD} which handles
     * this error and allows the tweet to serialize correctly.
     */
    @Override
    public byte[] serialize(String topic, Tweet data) {

        byte[] tweetBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(SERIALIZE_EXCEPT_ERR_USER_FIELD);
        try {

            tweetBytes= objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return tweetBytes;
    }

}

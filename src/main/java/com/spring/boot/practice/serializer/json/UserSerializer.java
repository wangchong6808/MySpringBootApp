package com.spring.boot.practice.serializer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.boot.practice.model.User;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Created by wangchong on 9/18/16.
 */
@JsonComponent
public class UserSerializer {

    /*public static class Serializer extends JsonSerializer<User> {

        @Override
        public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            //jsonGenerator.
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("ID",user.getId());
            jsonGenerator.writeStringField("Name",user.getName());
            jsonGenerator.writeStringField("Introduction",user.getIntroduction());
            jsonGenerator.writeEndObject();
        }

    }*/
    /*
    public static class Deserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return null;
        }
    }*/
}



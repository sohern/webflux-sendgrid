package io.ztech.cloud.webfluxdemo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Message.MessageBuilder.class)
@Builder(builderClassName = "MessageBuilder", toBuilder = true)
public class Message {
    private final String from;
    private final String to;
    private final String subject;
    private final String body;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MessageBuilder { }
}


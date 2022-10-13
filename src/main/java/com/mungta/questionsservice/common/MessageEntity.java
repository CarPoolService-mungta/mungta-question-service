package com.mungta.questionsservice.common;

import lombok.*;

@Getter
public class MessageEntity<T> {
    private Integer status;
    private String message;

    private MessageEntity(ApiMessage apiMessage) {
        this.status = apiMessage.getCode();
        this.message = apiMessage.getMessage();
    }

    public static MessageEntity of(ApiMessage apiMessage){
        return new MessageEntity(apiMessage);
    }
}

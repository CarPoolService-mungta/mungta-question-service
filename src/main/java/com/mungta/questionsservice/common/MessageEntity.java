package com.mungta.questionsservice.common;

import lombok.*;

@Getter
public class MessageEntity<T> {
    private Integer status;
    private String message;

    private MessageEntity(ApiStatus apiStatus) {
        this.status = apiStatus.getCode();
        this.message = apiStatus.getMessage();
    }

    public static MessageEntity of(ApiStatus apiStatus){
        return new MessageEntity(apiStatus);
    }
}

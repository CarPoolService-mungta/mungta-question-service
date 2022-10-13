package com.mungta.questionsservice.common;

import lombok.Getter;

public class CustomException extends RuntimeException {
    @Getter
    private ApiMessage apiMessage;

    public CustomException(ApiMessage apiMessage) {
        super(apiMessage.getMessage());
        this.apiMessage = apiMessage;
    }

    public CustomException(String apiStatus) {
        super(apiStatus);
    }
}

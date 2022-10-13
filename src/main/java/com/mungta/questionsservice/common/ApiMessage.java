package com.mungta.questionsservice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ApiMessage {
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -1, "예상치 못한 에러가 발생하였습니다."),
    //-100 Question 에러
    NOT_EXIST_QUESTION(HttpStatus.INTERNAL_SERVER_ERROR,-101, "문의사항이 존재하지 않습니다."),
    ;
    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    ApiMessage(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ApiMessage of(String message) {
        return Arrays.stream(ApiMessage.values())
                .filter(apiStatus -> apiStatus.getMessage().equals(message))
                .findFirst()
                .orElse(null);
    }
}

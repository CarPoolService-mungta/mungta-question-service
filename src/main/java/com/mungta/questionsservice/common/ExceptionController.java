package com.mungta.questionsservice.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<MessageEntity> handleApiException(CustomException e) {

        ApiMessage apiMessage = e.getApiMessage();
        return new ResponseEntity<>(MessageEntity.of(apiMessage), apiMessage.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<MessageEntity> handleException(Exception e) {

        ApiMessage apiMessage = ApiMessage.UNEXPECTED_ERROR;
        return new ResponseEntity<>(MessageEntity.of(apiMessage), apiMessage.getHttpStatus());
    }
}

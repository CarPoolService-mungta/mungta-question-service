package com.mungta.questionsservice.domain.question.model.enums;

import lombok.Getter;

@Getter
public enum DisplayStatus {
    SHOW("정상"),
    DELETE("삭제")
    ;

    private String status;

    DisplayStatus(String status){
        this.status=status;
    }
}

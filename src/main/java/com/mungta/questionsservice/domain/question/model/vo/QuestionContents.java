package com.mungta.questionsservice.domain.question.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class QuestionContents {

    private String title;
    private String body;
}

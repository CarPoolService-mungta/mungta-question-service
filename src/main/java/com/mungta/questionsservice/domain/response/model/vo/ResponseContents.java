package com.mungta.questionsservice.domain.response.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ResponseContents {

    private String title;
    private String body;
}

package com.mungta.questionsservice.domain.response.model.vo;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "답변 제목", required = true, example = "제목입니다.")
    private String title;

    @ApiModelProperty(value = "답변 내용", required = true, example = "내용입니다.")
    private String body;
}

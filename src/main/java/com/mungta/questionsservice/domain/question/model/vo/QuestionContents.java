package com.mungta.questionsservice.domain.question.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Embeddable;

@ApiModel
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class QuestionContents {

    @ApiModelProperty(value = "문의사항 제목", required = true, example = "제목입니다.")
    private String title;

    @ApiModelProperty(value = "문의사항 내용", required = true, example = "내용입니다.")
    private String body;
}

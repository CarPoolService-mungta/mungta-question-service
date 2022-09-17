package com.mungta.questionsservice.domain.question.dto.request;

import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@ApiModel
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRegisterRequest {

    @ApiModelProperty(value = "문의사항 컨텐츠", required = true)
    private QuestionContents question;
}

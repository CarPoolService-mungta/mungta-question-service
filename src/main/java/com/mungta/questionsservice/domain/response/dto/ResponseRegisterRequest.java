package com.mungta.questionsservice.domain.response.dto;

import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegisterRequest {

    @ApiModelProperty(value = "관리자 id", required = true, example = "1")
    private Long adminId;

    @ApiModelProperty(value = "답변 컨텐츠", required = true)
    private ResponseContents responseContents;

    @ApiModelProperty(value = "문의사항 id", required = true)
    private Long QuestionId;
}

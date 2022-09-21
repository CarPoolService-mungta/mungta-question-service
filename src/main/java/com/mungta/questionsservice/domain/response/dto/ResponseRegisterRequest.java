package com.mungta.questionsservice.domain.response.dto;

import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRegisterRequest {

    @ApiModelProperty(value = "답변 컨텐츠", required = true)
    private ResponseContents responseContents;

    @ApiModelProperty(value = "문의사항 id", required = true)
    private Long questionId;
}

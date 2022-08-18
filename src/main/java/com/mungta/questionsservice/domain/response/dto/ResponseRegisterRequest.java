package com.mungta.questionsservice.domain.response.dto;

import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegisterRequest {

    private Long adminId;
    private ResponseContents responseContents;
    private Long QuestionId;
}

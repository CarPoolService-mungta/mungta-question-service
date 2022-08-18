package com.mungta.questionsservice.domain.question.dto.response;

import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class QuestionResponse {
    private QuestionContents question;
    private ResponseContents response;
}

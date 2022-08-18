package com.mungta.questionsservice.domain.question.dto.request;

import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRegisterRequest {

    private Long userId;
    private QuestionContents question;
}

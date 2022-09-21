package com.mungta.questionsservice.domain.question.model;

import com.mungta.questionsservice.domain.BaseEntity;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.response.model.Response;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Embedded
    private QuestionContents question;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="response_id")
    private Response response;

    @Enumerated(EnumType.STRING)
    private DisplayStatus displayStatus;

    public static Question of(QuestionContents question, String userId){
        return Question.builder()
                .question(question)
                .userId(userId)
                .displayStatus(DisplayStatus.SHOW)
                .build();
    }

    public QuestionListResponse convertListResponse(){
        return QuestionListResponse.of(
                id,
                question.getTitle(),
                Objects.isNull(getCreatedDate()) ? null : getCreatedDate().toLocalDate() ,
                response != null
        );
    }

    public QuestionResponse convertQuestionResponse(){
        return new QuestionResponse(question, Objects.isNull(response) ? null : response.getResponse());
    }

}

package com.mungta.questionsservice.domain.response.model;


import com.mungta.questionsservice.domain.BaseEntity;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ResponseContents response;

    private Long adminId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Question question;

    public static Response of(ResponseContents response, Long adminId, Question question){
        return Response.builder()
                .response(response)
                .adminId(adminId)
                .question(question)
                .build();
    }


}

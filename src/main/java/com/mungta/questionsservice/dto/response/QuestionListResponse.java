package com.mungta.questionsservice.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@Builder
public class QuestionListResponse {

    private Long id;
    private String title;
    private LocalDate createdDate;
    private Boolean existResponse;

    public static QuestionListResponse of(Long id,
                                          String title,
                                          LocalDate createdDate,
                                          Boolean existResponse){
        return QuestionListResponse.builder()
                .id(id)
                .title(title)
                .createdDate(createdDate)
                .existResponse(existResponse)
                .build();
    }
}

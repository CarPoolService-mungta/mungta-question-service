package com.mungta.questionsservice.domain.response.service;

import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.repository.QuestionRepository;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.dto.ResponseRegisterRequest;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.mungta.questionsservice.domain.question.sample.QuestionTestSample.*;
import static com.mungta.questionsservice.domain.response.sample.ResponseTestSample.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
public class ResponseServiceTest {
    @InjectMocks
    @Spy
    private ResponseService responseService;

    @Mock
    private QuestionRepository questionRepository;

    private Question question;

    private final LocalDateTime nowDateTime = LocalDateTime.now();

    @BeforeEach
    void setUp(){

        question = Question.builder()
                .id(QUESTION_ID)
                .userId(QUESTION_USER_ID)
                .question(
                        QuestionContents.builder()
                                .title(QUESTION_TITLE)
                                .body(QUESTION_BODY)
                                .build()
                )
                .response(
                        Response.builder()
                                .response(
                                        ResponseContents.builder()
                                                .title(RESPONSE_TITLE)
                                                .body(RESPONSE_BODY)
                                                .build()
                                )
                                .build()
                )
                .displayStatus(QUESTION_DISPLAY_STATUS)
                .build();
    }

    @DisplayName("문의 등록")
    @Test
    void registerResponse() {

        responseService.registerResponse(RESPONSE_ADMIN_ID,
                question,
                ResponseRegisterRequest.builder()
                        .responseContents(
                                ResponseContents.builder()
                                        .title(RESPONSE_TITLE)
                                        .body(RESPONSE_BODY)
                                        .build()
                        )
                        .questionId(QUESTION_ID)
                        .build());


        verify(questionRepository).save(any());

    }

}

package com.mungta.questionsservice.domain.question.service;

import com.mungta.questionsservice.common.ApiException;
import com.mungta.questionsservice.common.ApiStatus;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.repository.QuestionRepository;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mungta.questionsservice.domain.question.sample.QuestionTestSample.*;
import static com.mungta.questionsservice.domain.response.sample.ResponseTestSample.RESPONSE_BODY;
import static com.mungta.questionsservice.domain.response.sample.ResponseTestSample.RESPONSE_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
public class QuestionServiceTest {

    @InjectMocks
    @Spy
    private QuestionService questionService;

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
    void registerQuestion() {
        questionService.registerQuestion(QUESTION_USER_ID, QUESTION_REGISTER_REQUEST);

        verify(questionRepository).save(any());
    }

    @DisplayName("문의 ID 조회")
    @Test
    void findShowQuestionById() {
        given(questionRepository.findByIdAndDisplayStatus(QUESTION_ID, DisplayStatus.SHOW))
                .willReturn(Optional.ofNullable(question));

        Question question1 = questionService.findShowQuestionById(QUESTION_ID);

        assertAll(
                ()->assertThat(question1.getId()).isEqualTo(QUESTION_ID),
                ()->assertThat(question1.getUserId()).isEqualTo(QUESTION_USER_ID),
                ()->assertThat(question1.getQuestion().getTitle()).isEqualTo(QUESTION_TITLE),
                ()->assertThat(question1.getQuestion().getBody()).isEqualTo(QUESTION_BODY),
                ()->assertThat(question1.getResponse().getResponse().getTitle()).isEqualTo(RESPONSE_TITLE),
                ()->assertThat(question1.getResponse().getResponse().getBody()).isEqualTo(RESPONSE_BODY),
                ()->assertThat(question1.getDisplayStatus()).isEqualTo(QUESTION_DISPLAY_STATUS)
        );
    }
    @DisplayName("문의 ID 조회했는데 등록된 ID가 없는 경우 Exception 발생")
    @Test
    void findShowQuestionById_not_exist_question() {
        given(questionRepository.findByIdAndDisplayStatus(2L,DisplayStatus.SHOW))
                .willThrow(new ApiException(ApiStatus.NOT_EXIST_QUESTION));

        assertThatThrownBy(()->questionService.findShowQuestionById(2L))
                .isInstanceOf(ApiException.class)
                .hasMessage("문의사항이 존재하지 않습니다.");
    }

    @DisplayName("question response로 convert")
    @Test
    void findShowQuestionResponse() {
        given(questionRepository.findByIdAndDisplayStatus(QUESTION_ID, DisplayStatus.SHOW))
                .willReturn(Optional.ofNullable(question));

        QuestionResponse questionResponse = questionService.findShowQuestionResponse(QUESTION_ID);

        assertAll(
                ()->assertThat(questionResponse.getQuestion().getTitle()).isEqualTo(QUESTION_TITLE),
                ()->assertThat(questionResponse.getQuestion().getBody()).isEqualTo(QUESTION_BODY),
                ()->assertThat(questionResponse.getResponse().getTitle()).isEqualTo(RESPONSE_TITLE),
                ()->assertThat(questionResponse.getResponse().getBody()).isEqualTo(RESPONSE_BODY)
                );
    }

    @DisplayName("유저 문의 조회")
    @Test
    void findByUserId() {
        given(questionRepository.findAllByUserIdAndDisplayStatusOrderByCreatedDateDesc(QUESTION_USER_ID, DisplayStatus.SHOW))
                .willReturn(List.of(question));

        List<QuestionListResponse> response = questionService.findByUserId(QUESTION_USER_ID);

        assertAll(
                ()->assertThat(response.size()).isEqualTo(1),
                ()->assertThat(response.get(0).getId()).isEqualTo(QUESTION_ID),
                ()->assertThat(response.get(0).getTitle()).isEqualTo(QUESTION_TITLE),
                ()->assertThat(response.get(0).getExistResponse()).isEqualTo(true)
        );
    }

    @DisplayName("[관리자] 전체 문의 조회")
    @Test
    void findAllQuestion() {
        given(questionRepository.findAllByDisplayStatusOrderByCreatedDateDesc(DisplayStatus.SHOW))
                .willReturn(List.of(question));

        List<QuestionListResponse> response = questionService.findAllQuestion();

        assertAll(
                ()->assertThat(response.size()).isEqualTo(1),
                ()->assertThat(response.get(0).getId()).isEqualTo(QUESTION_ID),
                ()->assertThat(response.get(0).getTitle()).isEqualTo(QUESTION_TITLE),
                ()->assertThat(response.get(0).getExistResponse()).isEqualTo(true)
        );
    }

    @DisplayName("문의 삭제")
    @Test
    void deleteQuestion() {
        given(questionRepository.findByIdAndDisplayStatus(QUESTION_ID, DisplayStatus.SHOW))
                .willReturn(Optional.ofNullable(question));

        questionService.deleteQuestion(QUESTION_ID);

        verify(questionRepository).save(any());
    }
}

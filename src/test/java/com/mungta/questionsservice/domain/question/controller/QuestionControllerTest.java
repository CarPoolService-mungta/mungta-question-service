package com.mungta.questionsservice.domain.question.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static com.mungta.questionsservice.domain.question.sample.QuestionTestSample.*;
import static com.mungta.questionsservice.domain.response.sample.ResponseTestSample.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private QuestionService questionService;

    private Question question;

    @BeforeEach
    void setUp(){
        //한글 깨짐...
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

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
    @DisplayName("[회원] 문의사항 등록 API")
    @Test
    void registerQuestion() throws Exception{

        doNothing()
                .when(questionService).registerQuestion(anyString(), any());

        ResultActions result = mockMvc.perform(
                post("/api/question/question")
                        .header("userId", QUESTION_USER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(QUESTION_REGISTER_REQUEST))
        );

        result.andExpect(status().isNoContent());
    }

    @DisplayName("[회원] 특정 문의사항 조회 API")
    @Test
    void findShowQuestionById() throws Exception {
//        doReturn(
//                QuestionResponse.builder()
//                        .question(
//                                QuestionContents.builder()
//                                        .title(QUESTION_TITLE)
//                                        .body(QUESTION_BODY)
//                                        .build()
//                        )
//                        .response(
//                                ResponseContents.builder()
//                                        .title(RESPONSE_TITLE)
//                                        .body(RESPONSE_BODY)
//                                        .build()
//                        ).build()
//        ).when(questionService).findShowQuestionResponse(QUESTION_ID);
        doReturn(
                question.convertQuestionResponse()
        ).when(questionService).findShowQuestionResponse(QUESTION_ID);


        ResultActions resultActions = mockMvc.perform(
                get("/api/question/question-show")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id",QUESTION_ID.toString())
        );

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.question.title").value(QUESTION_TITLE))
                .andExpect(jsonPath("$.question.body").value(QUESTION_BODY))
                .andExpect(jsonPath("$.response.title").value(RESPONSE_TITLE))
                .andExpect(jsonPath("$.response.body").value(RESPONSE_BODY));
    }


    @DisplayName("[회원] 특정 유저 문의사항 조회 API")
    @Test
    void findByUserId() throws Exception {
        doReturn(List.of(QuestionListResponse.builder()
                .id(QUESTION_ID)
                .title(QUESTION_TITLE)
                .existResponse(true)
                .build()
        ))
                .when(questionService).findByUserId(QUESTION_USER_ID);

        ResultActions resultActions = mockMvc.perform(
                get("/api/question/question-show-by-userId")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("userId", QUESTION_USER_ID)
        );

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(QUESTION_ID))
                .andExpect(jsonPath("$[0].title").value(QUESTION_TITLE))
                .andExpect(jsonPath("$[0].existResponse").value(true));
    }

    @DisplayName("[회원] 특정 문의사항 삭제 API")
    @Test
    void deleteQuestion() throws Exception {
        doNothing()
                .when(questionService).deleteQuestion(QUESTION_ID);

        ResultActions resultActions = mockMvc.perform(
                delete("/api/question/question")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", QUESTION_ID.toString())
        );

        resultActions.andExpect(status().isNoContent());
    }

    @DisplayName("[ADMIN]모든 문의사항 조회 API")
    @Test
    void findAllQuestion() throws Exception {
        doReturn(List.of(QuestionListResponse.of(
                QUESTION_ID,
                QUESTION_TITLE,
                null,
                true
        ))).when(questionService).findAllQuestion();

        ResultActions resultActions = mockMvc.perform(
                get("/api/question/admin/question-show-all")
                        .accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(QUESTION_ID))
                .andExpect(jsonPath("$[0].title").value(QUESTION_TITLE))
                .andExpect(jsonPath("$[0].existResponse").value(true));
    }

}

package com.mungta.questionsservice.domain.question.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mungta.questionsservice.domain.question.dto.request.QuestionRegisterRequest;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.sample.QuestionSample;
import com.mungta.questionsservice.domain.question.service.QuestionService;
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

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private List<Question> questions;

    @BeforeEach
    void setUp(){
//        questionService=mock(QuestionService.class, CALLS_REAL_METHODS);

        questions=new ArrayList<>(){
            {
                add(QuestionSample.question1);
                add(QuestionSample.question2);
                add(QuestionSample.question3);
                add(QuestionSample.question4);
                add(QuestionSample.question5);
                add(QuestionSample.question6);
                add(QuestionSample.question7);
                add(QuestionSample.question8);
                add(QuestionSample.question9);
                add(QuestionSample.question10);
            }
        };
        QuestionSample.init();
    }

    @DisplayName("[문의사항] 문의 사항 등록 API")
    @Test
    void registerQuestion() throws Exception{

        doNothing()
                .when(questionService).registerQuestion(any());

        ResultActions resultActions = mockMvc.perform(
                post("/api/question/question")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new QuestionRegisterRequest(0L,new QuestionContents("추가 질문 제목", "추가 질문 내용"))))
        );

        resultActions.andExpect(status().isNoContent());
    }

    @DisplayName("[문의사항] 특정 문의 사항 조회 API")
    @Test
    void findShowQuestionById() throws Exception{
        Long testQuestionId = 0L;

        doReturn(questions.get(0))
                .when(questionService).findShowQuestionById(testQuestionId);

        ResultActions resultActions = mockMvc.perform(
                get("/api/question/question-show")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", "0")
        );


        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(questions.get(0).getId()))
                .andExpect(jsonPath("$.question.title").value(questions.get(0).getQuestion().getTitle()))
                .andExpect(jsonPath("$.question.body").value(questions.get(0).getQuestion().getBody()))
                .andExpect(jsonPath("$.displayStatus").value(questions.get(0).getDisplayStatus()));
    }
}

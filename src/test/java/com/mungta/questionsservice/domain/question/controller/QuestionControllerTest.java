package com.mungta.questionsservice.domain.question.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mungta.questionsservice.domain.question.dto.request.QuestionRegisterRequest;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.sample.QuestionSample;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc
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

//        doReturn(questions.get(testQuestionId.intValue()).convertQuestionResponse())
//                .when(questionService).findShowQuestionResponse(testQuestionId);
        when(questionService.findShowQuestionResponse(testQuestionId))
                .thenReturn(questions.get(testQuestionId.intValue()).convertQuestionResponse());

        ResultActions resultActions = mockMvc.perform(
                get("/api/question/question-show")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", testQuestionId.toString())
        );


        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.question.title").value(questions.get(testQuestionId.intValue()).getQuestion().getTitle()))
                .andExpect(jsonPath("$.question.body").value(questions.get(testQuestionId.intValue()).getQuestion().getBody()))
                .andExpect(jsonPath("$.response.title").value(questions.get(testQuestionId.intValue()).getResponse().getResponse().getTitle()))
                .andExpect(jsonPath("$.response.body").value(questions.get(testQuestionId.intValue()).getResponse().getResponse().getBody()))
                ;
    }

    @DisplayName("[문의사항] 특정 유저 문의사항 조회 API")
    @Test
    void findByUserId() throws Exception{
        Long testUserId = 0L;

        List<QuestionListResponse> expectResult = new ArrayList<>(){
            {
                add(convertListResponse(questions.get(0)));
//                add(questions.get(1).convertListResponse());
                add(convertListResponse(questions.get(2)));
            }
        };
        //무슨 의미가 있지... 보니까 여기서 예상 값으로 넣어준 것이 json으로 들어가고 그대로 뒤에서 확인하는거같은데.. 이정도면 a==a 아면가?
//        doReturn(expectResult)
//                .when(questionService).findByUserId(testUserId);
        when(questionService.findByUserId(testUserId))
                .thenReturn(expectResult);

        ResultActions resultActions = mockMvc.perform(
                get("/api/question/question-show-by-userId")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("userId", testUserId.toString())
        );


        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(convertListResponse(questions.get(0)).getId()))
                .andExpect(jsonPath("$[0].title").value(convertListResponse(questions.get(0)).getTitle()))
                .andExpect(jsonPath("$[0].existResponse").value(convertListResponse(questions.get(0)).getExistResponse()))
                .andExpect(jsonPath("$[1].id").value(convertListResponse(questions.get(2)).getId()))
                .andExpect(jsonPath("$[1].title").value(convertListResponse(questions.get(2)).getTitle()))
                .andExpect(jsonPath("$[1].existResponse").value(convertListResponse(questions.get(2)).getExistResponse()))
        ;
    }
    public QuestionListResponse convertListResponse(Question question){
        return QuestionListResponse.of(
                question.getId(),
                question.getQuestion().getTitle(),
                null,
                question.getResponse() != null
        );
    }

}

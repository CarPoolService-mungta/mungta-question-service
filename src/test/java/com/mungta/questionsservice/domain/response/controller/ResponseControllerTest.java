package com.mungta.questionsservice.domain.response.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mungta.questionsservice.domain.question.controller.QuestionController;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.dto.ResponseRegisterRequest;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;
import com.mungta.questionsservice.domain.response.service.ResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

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
@WebMvcTest(ResponseController.class)
public class ResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ResponseService responseService;
    @MockBean
    private QuestionService questionService;

    @BeforeEach
    void setUp(){
        //한글 깨짐...
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @DisplayName("[ADMIN]답변 등록 API")
    @Test
    void registerResponse() throws Exception{
        doNothing()
                .when(responseService)
                .registerResponse(anyString(), any(), any());

        ResultActions resultActions = mockMvc.perform(
                post("/api/response/admin/response")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userId", RESPONSE_ADMIN_ID)
                        .content(new ObjectMapper()
                                .writeValueAsString(
                                        ResponseRegisterRequest.builder()
                                                .responseContents(
                                                        ResponseContents.builder()
                                                                .title(RESPONSE_TITLE)
                                                                .body(RESPONSE_BODY)
                                                                .build()
                                                )
                                                .questionId(QUESTION_ID)
                                                .build()
                                ))
        );

        resultActions.andExpect(status().isNoContent());
    }


}

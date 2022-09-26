package com.mungta.questionsservice.domain.question.sample;

import com.mungta.questionsservice.dto.request.QuestionRegisterRequest;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;

import static com.mungta.questionsservice.domain.response.sample.ResponseTestSample.*;

public class QuestionTestSample {

    public static final Long QUESTION_ID=1L;
    public static final String QUESTION_USER_ID ="exceedlimit";
    public static final String QUESTION_TITLE = "제목";
    public static final String QUESTION_BODY ="내용";
    public static final DisplayStatus QUESTION_DISPLAY_STATUS = DisplayStatus.SHOW;
    public static final Response QUESTIONS_RESPONSE;
    static {
        QUESTIONS_RESPONSE = Response.builder()
                .id(RESPONSE_ID)
                .adminId(RESPONSE_ADMIN_ID)
                .response(
                        ResponseContents.builder()
                                .title(RESPONSE_TITLE)
                                .body(RESPONSE_BODY)
                        .build())
                .build();
    }

    public static final QuestionRegisterRequest QUESTION_REGISTER_REQUEST;
    static{
        QUESTION_REGISTER_REQUEST = QuestionRegisterRequest.builder()
                .question(
                        QuestionContents.builder()
                                .title(QUESTION_TITLE)
                                .body(QUESTION_BODY)
                                .build()
                )
                .build();
    }
}

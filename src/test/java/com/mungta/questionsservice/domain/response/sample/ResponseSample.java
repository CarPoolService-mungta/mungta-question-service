package com.mungta.questionsservice.domain.response.sample;

import com.mungta.questionsservice.domain.question.sample.QuestionSample;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.model.vo.ResponseContents;

public class ResponseSample {
    public static Response response1 = Response.of(new ResponseContents("첫번째 답변 제목", "첫번째 답변 내용"), "6L", QuestionSample.question1);
    public static Response response2 = Response.of(new ResponseContents("두번째 답변 제목", "두번째 답변 내용"), "6L", QuestionSample.question2);
    public static Response response5 = Response.of(new ResponseContents("세번째 답변 제목", "세번째 답변 내용"), "6L", QuestionSample.question5);
    public static Response response6 = Response.of(new ResponseContents("네번째 답변 제목", "네번째 답변 내용"), "6L", QuestionSample.question6);
    public static Response response8 = Response.of(new ResponseContents("다섯번째 답변 제목", "다섯번째 답변 내용"), "6L", QuestionSample.question8);
}

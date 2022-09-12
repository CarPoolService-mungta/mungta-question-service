package com.mungta.questionsservice.domain.question.sample;

import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.vo.QuestionContents;
import com.mungta.questionsservice.domain.response.sample.ResponseSample;

public class QuestionSample {
    public static Question question1 =  Question.of(new QuestionContents("첫번째 문의 제목", "첫번째 문의 답변"), 0L);
    public static Question question2 =  Question.of(new QuestionContents("두번째 문의 제목", "두번째 문의 답변"), 0L);
    public static Question question3 =  Question.of(new QuestionContents("세번째 문의 제목", "세번째 문의 답변"), 0L);
    public static Question question4 =  Question.of(new QuestionContents("네번째 문의 제목", "네번째 문의 답변"), 1L);
    public static Question question5 =  Question.of(new QuestionContents("다섯번째 문의 제목", "다섯번째 문의 답변"), 1L);
    public static Question question6 =  Question.of(new QuestionContents("여섯번째 문의 제목", "여섯번째 문의 답변"), 2L);
    public static Question question7 =  Question.of(new QuestionContents("일곱번째 문의 제목", "일곱번째 문의 답변"), 2L);
    public static Question question8 =  Question.of(new QuestionContents("여덟번째 문의 제목", "여덟번째 문의 답변"), 3L);
    public static Question question9 =  Question.of(new QuestionContents("아홉번째 문의 제목", "아홉번째 문의 답변"), 3L);
    public static Question question10 =  Question.of(new QuestionContents("열번째 문의 제목", "열번째 문의 답변"), 3L);

    public static void init(){
        question1.setId(0L);
        question2.setId(1L);
        question3.setId(2L);
        question4.setId(3L);
        question5.setId(4L);
        question6.setId(5L);
        question7.setId(6L);
        question8.setId(7L);
        question9.setId(8L);
        question10.setId(9L);


        question2.setDisplayStatus(DisplayStatus.DELETE);
        question4.setDisplayStatus(DisplayStatus.DELETE);

        question1.setResponse(ResponseSample.response1);
        question2.setResponse(ResponseSample.response2);
        question5.setResponse(ResponseSample.response5);
        question6.setResponse(ResponseSample.response6);
        question8.setResponse(ResponseSample.response8);

    }
}

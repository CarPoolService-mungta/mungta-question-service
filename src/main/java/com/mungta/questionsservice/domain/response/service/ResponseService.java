package com.mungta.questionsservice.domain.response.service;

import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.repository.QuestionRepository;
import com.mungta.questionsservice.domain.response.model.Response;
import com.mungta.questionsservice.domain.response.repository.ResponseRepository;
import com.mungta.questionsservice.domain.response.dto.ResponseRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void registerResponse(Question question,
                                 ResponseRegisterRequest responseRegisterRequest){

        question.setResponse(Response.of(responseRegisterRequest.getResponseContents(),
                responseRegisterRequest.getAdminId(),
                question
        ));
        questionRepository.save(question);
//        responseRepository.save(Response.of(responseRegisterRequest.getResponseContents(),
//                responseRegisterRequest.getAdminId(),
//                question
//        ));
    }
}

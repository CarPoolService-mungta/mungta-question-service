package com.mungta.questionsservice.domain.response.service;

import com.mungta.questionsservice.domain.question.model.Question;
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

    @Transactional
    public void registerResponse(Question question,
                                 ResponseRegisterRequest responseRegisterRequest){

        responseRepository.save(Response.of(responseRegisterRequest.getResponseContents(),
                responseRegisterRequest.getAdminId(),
                question
        ));
    }
}

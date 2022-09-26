package com.mungta.questionsservice.domain.question.service;

import com.mungta.questionsservice.common.ApiException;
import com.mungta.questionsservice.common.ApiStatus;
import com.mungta.questionsservice.dto.response.QuestionListResponse;
import com.mungta.questionsservice.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.repository.QuestionRepository;
import com.mungta.questionsservice.dto.request.QuestionRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public void registerQuestion(String userId, QuestionRegisterRequest questionRegisterRequest){
        questionRepository.save(Question.of(questionRegisterRequest.getQuestion(),userId));
    }

    public Question findShowQuestionById(Long id){

        return questionRepository.findByIdAndDisplayStatus(id, DisplayStatus.SHOW)
                .orElseThrow(()-> new ApiException(ApiStatus.NOT_EXIST_QUESTION));
    }

    public QuestionResponse findShowQuestionResponse(Long id){
        return findShowQuestionById(id).convertQuestionResponse();
    }

    public List<QuestionListResponse> findByUserId(String userId){
        return questionRepository.findAllByUserIdAndDisplayStatusOrderByCreatedDateDesc(userId,DisplayStatus.SHOW)
                .stream().map(Question::convertListResponse)
                .collect(Collectors.toList());
    }

    public List<QuestionListResponse> findAllQuestion(){
        return questionRepository.findAllByDisplayStatusOrderByCreatedDateDesc(DisplayStatus.SHOW)
                .stream().map(Question::convertListResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteQuestion(Long id){
        Question question = findShowQuestionById(id);
        question.setDisplayStatus(DisplayStatus.DELETE);
        questionRepository.save(question);
    }

}

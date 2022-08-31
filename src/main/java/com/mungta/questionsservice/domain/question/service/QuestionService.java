package com.mungta.questionsservice.domain.question.service;

import com.mungta.questionsservice.common.ApiException;
import com.mungta.questionsservice.common.ApiStatus;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.enums.DisplayStatus;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.repository.QuestionRepository;
import com.mungta.questionsservice.domain.question.dto.request.QuestionRegisterRequest;
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
    public void registerQuestion(QuestionRegisterRequest questionRegisterRequest){
        questionRepository.save(Question.of(questionRegisterRequest.getQuestion(),questionRegisterRequest.getUserId()));
    }

    public Question findShowQuestionById(Long id){

        return questionRepository.findByIdAndDisplayStatus(id, DisplayStatus.SHOW)
                .orElseThrow(()-> new ApiException(ApiStatus.NOT_EXIST_QUESTION));
    }

    public QuestionResponse findShowQuestionResponse(Long id){
        return findShowQuestionById(id).convertQuestionResponse();
    }

    public List<QuestionListResponse> findByUserId(Long userId){
        return questionRepository.findAllByUserIdAndDisplayStatus(userId,DisplayStatus.SHOW)
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

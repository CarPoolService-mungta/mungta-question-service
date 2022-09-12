package com.mungta.questionsservice.domain.question.controller;

import com.mungta.questionsservice.common.ApiException;
import com.mungta.questionsservice.common.ApiStatus;
import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.model.Question;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.question.dto.request.QuestionRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;
    // Todo Swagger 코멘트 달아야합니다.
    @PostMapping("/question")
    public ResponseEntity registerQuestion(@RequestBody QuestionRegisterRequest questionRegisterRequest){
        questionService.registerQuestion(questionRegisterRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/question-show")
    public ResponseEntity<QuestionResponse> findShowQuestionById(@RequestParam Long id){

        return ResponseEntity.ok(questionService.findShowQuestionResponse(id));
    }

    @GetMapping("/question-show-by-userId")
    public ResponseEntity<List<QuestionListResponse>> findByUserId(@RequestParam Long userId){

        return ResponseEntity.ok(questionService.findByUserId(userId));
    }

    @DeleteMapping("/question")
    public ResponseEntity deleteQuestion(@RequestParam Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}

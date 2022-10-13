package com.mungta.questionsservice.controller;

import com.mungta.questionsservice.dto.response.QuestionListResponse;
import com.mungta.questionsservice.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.dto.request.QuestionRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = { "문의사항 Controller" })
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @ApiOperation(value = "문의사항 등록", notes = "문의사항 등록 api")
    @PostMapping("/question")
    public ResponseEntity registerQuestion(@ApiParam(value = "유저id", required = true) @RequestHeader("userId") String userId,
                                           @RequestBody QuestionRegisterRequest questionRegisterRequest){
        questionService.registerQuestion(userId, questionRegisterRequest);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "특정 문의사항 조회", notes = "특정 문의사항 조회 api")
    @GetMapping("/question-show")
    public ResponseEntity<QuestionResponse> findShowQuestionById(@ApiParam(value = "문의사항 id", required = true)  @RequestParam Long id){

        return ResponseEntity.ok(questionService.findShowQuestionResponse(id));
    }

    @ApiOperation(value = "특정 유저 문의사항 조회", notes = "특정 유저 문의사항 조회 api")
    @GetMapping("/question-show-by-userId")
//    public ResponseEntity<List<QuestionListResponse>> findByUserId(@ApiParam(value = "유저id", required = true) @RequestParam String userId){
    public ResponseEntity<List<QuestionListResponse>> findByUserId(@ApiParam(value = "유저id", required = true) @RequestHeader("userId") String userId){

        return ResponseEntity.ok(questionService.findByUserId(userId));
    }

    @ApiOperation(value = "특정 문의사항 삭제", notes = "특정 문의사항 삭제 api")
    @DeleteMapping("/question")
    public ResponseEntity deleteQuestion(@ApiParam(value = "문의사항 id", required = true) @RequestParam Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "[ADMIN]모든 문의사항 조회", notes = "[ADMIN]모든 문의사항 조회 api")
    @GetMapping("/admin/question-show-all")
    public ResponseEntity<List<QuestionListResponse>> findAllQuestion(){

        return ResponseEntity.ok(questionService.findAllQuestion());
    }
}

package com.mungta.questionsservice.domain.question.controller;

import com.mungta.questionsservice.domain.question.dto.response.QuestionListResponse;
import com.mungta.questionsservice.domain.question.dto.response.QuestionResponse;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.question.dto.request.QuestionRegisterRequest;
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
    // Todo Swagger 코멘트 달아야합니다.
    @ApiOperation(value = "문의사항 등록", notes = "문의사항 등록 api")
    @PostMapping("/question")
    public ResponseEntity registerQuestion(@RequestBody QuestionRegisterRequest questionRegisterRequest){
        questionService.registerQuestion(questionRegisterRequest);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/now")
//    public ResponseEntity now(){
//        return ResponseEntity.ok(System.currentTimeMillis());
//    }
//    @GetMapping("/exp")
//    public ResponseEntity exp(){
//        return ResponseEntity.ok(System.currentTimeMillis() + 24 * 60 * 60 * 1000L);
//    }
    @ApiOperation(value = "특정 문의사항 조회", notes = "특정 문의사항 조회 api")
    @GetMapping("/question-show")
    public ResponseEntity<QuestionResponse> findShowQuestionById(@ApiParam(value = "문의사항 id", required = true)  @RequestParam Long id){

        return ResponseEntity.ok(questionService.findShowQuestionResponse(id));
    }

    @ApiOperation(value = "특정 유저 문의사항 조회", notes = "특정 유저 문의사항 조회 api")
    @GetMapping("/question-show-by-userId")
    public ResponseEntity<List<QuestionListResponse>> findByUserId(@ApiParam(value = "유저id", required = true) @RequestParam Long userId){

        return ResponseEntity.ok(questionService.findByUserId(userId));
    }

    @ApiOperation(value = "특정 유저 문의사항 삭제", notes = "특정 유저 문의사항 삭제 api")
    @DeleteMapping("/question")
    public ResponseEntity deleteQuestion(@ApiParam(value = "문의사항 id", required = true) @RequestParam Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}

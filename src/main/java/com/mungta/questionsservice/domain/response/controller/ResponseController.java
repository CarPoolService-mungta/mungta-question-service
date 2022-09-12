package com.mungta.questionsservice.domain.response.controller;


import com.mungta.questionsservice.common.MessageEntity;
import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.service.ResponseService;
import com.mungta.questionsservice.domain.response.dto.ResponseRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = { "답변 Controller" })
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/response")
public class ResponseController {

    private final ResponseService responseService;
    private final QuestionService questionService;

    @ApiOperation(value = "답변 등록", notes = "답변 등록 api")
    @PostMapping("/response")
    public ResponseEntity registerResponse(@RequestBody ResponseRegisterRequest responseRegisterRequest){
        responseService.registerResponse(questionService.findShowQuestionById(responseRegisterRequest.getQuestionId())
                , responseRegisterRequest);

        return ResponseEntity.noContent().build();
    }
}

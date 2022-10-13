package com.mungta.questionsservice.controller;


import com.mungta.questionsservice.domain.question.service.QuestionService;
import com.mungta.questionsservice.domain.response.service.ResponseService;
import com.mungta.questionsservice.dto.request.ResponseRegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = { "답변 Controller" })
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/response")
public class ResponseController {

    private final ResponseService responseService;
    private final QuestionService questionService;

    @ApiOperation(value = "[ADMIN]답변 등록", notes = "[ADMIN]답변 등록 api")
    @PostMapping("/admin/response")
    public ResponseEntity registerResponse(@ApiParam(value = "유저id", required = true) @RequestHeader("userId") String adminId,
                                            @RequestBody ResponseRegisterRequest responseRegisterRequest){
        responseService.registerResponse(adminId, questionService.findShowQuestionById(responseRegisterRequest.getQuestionId())
                , responseRegisterRequest);

        return ResponseEntity.noContent().build();
    }
}

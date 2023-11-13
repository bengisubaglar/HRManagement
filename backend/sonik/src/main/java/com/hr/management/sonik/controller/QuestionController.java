package com.hr.management.sonik.controller;

import com.hr.management.sonik.controller.dto.AddAnswerRequestDto;
import com.hr.management.sonik.controller.dto.AddQuestionRequestDto;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.BASE_API_URL + "/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping( "/findAllByTestId")
    public ResponseEntity<Object> findAllByTestId(@RequestParam Long testId) {
        return ResponseEntity.ok().body(questionService.findAllByTestId(testId));
    }

    @PostMapping( "/addQuestion")
    public ResponseEntity<Object> addQuestion(@RequestBody AddQuestionRequestDto requestDto) {
        questionService.addQuestion(requestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping( "/addAnswer")
    public ResponseEntity<Object> addAnswer(@RequestBody AddAnswerRequestDto requestDto) {
        questionService.addAnswer(requestDto);
        return ResponseEntity.ok().build();
    }
}

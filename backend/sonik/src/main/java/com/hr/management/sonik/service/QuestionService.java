package com.hr.management.sonik.service;

import com.hr.management.sonik.controller.dto.AddAnswerRequestDto;
import com.hr.management.sonik.controller.dto.AddQuestionRequestDto;
import com.hr.management.sonik.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAllByTestId(Long testId);

    void addQuestion(AddQuestionRequestDto requestDto);
    void addAnswer(AddAnswerRequestDto requestDto);
}

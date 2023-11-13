package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.controller.dto.AddAnswerRequestDto;
import com.hr.management.sonik.controller.dto.AddQuestionRequestDto;
import com.hr.management.sonik.entity.Question;
import com.hr.management.sonik.repository.AnswerRepository;
import com.hr.management.sonik.repository.QuestionRepository;
import com.hr.management.sonik.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<Question> findAllByTestId(Long testId) {
        return questionRepository.findAllByTest(testId);
    }

    @Override
    public void addQuestion(AddQuestionRequestDto requestDto) {
        questionRepository.addQuestion(requestDto.getTestId(), requestDto.getTitle(), requestDto.getDescription(), requestDto.getMark());
    }

    @Override
    public void addAnswer(AddAnswerRequestDto requestDto) {
        answerRepository.addAnswer(requestDto.getQuestionId(), requestDto.getOptions(), requestDto.getIsCorrect());
    }
}

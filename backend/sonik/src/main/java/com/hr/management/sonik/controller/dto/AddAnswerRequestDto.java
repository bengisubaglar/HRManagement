package com.hr.management.sonik.controller.dto;

import lombok.Data;

@Data
public class AddAnswerRequestDto {

    private Long questionId;
    private String options;
    private Boolean isCorrect;
}

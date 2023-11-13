package com.hr.management.sonik.controller.dto;

import lombok.Data;

@Data
public class AddQuestionRequestDto {

    private Long testId;
    private String title;
    private String description;
    private String mark;
}

package com.hr.management.sonik.controller.dto;

import lombok.Data;

@Data
public class UpdateStageStatusRequestDto {

    private Long id;
    private Integer score;
    private Long stageId;
    private Boolean passed;
}
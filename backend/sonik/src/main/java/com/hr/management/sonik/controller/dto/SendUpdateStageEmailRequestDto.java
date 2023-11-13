package com.hr.management.sonik.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SendUpdateStageEmailRequestDto {

    private Boolean passed;
    private Long stageId;
    private String to;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

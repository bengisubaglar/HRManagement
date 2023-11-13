package com.hr.management.sonik.controller.dto;

import lombok.Data;

@Data
public class UpdateInterviewRequestDto {

    private Long hrId;
    private String candidateEmail;
    private Boolean isPassed;
    private Boolean forceUpdate;

}

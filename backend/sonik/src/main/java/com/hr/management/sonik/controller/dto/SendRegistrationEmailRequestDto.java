package com.hr.management.sonik.controller.dto;

import lombok.Data;

@Data
public class SendRegistrationEmailRequestDto {

    private String to;
    private String subject;

}

package com.hr.management.sonik.service;

import com.hr.management.sonik.controller.dto.SendRegistrationEmailRequestDto;
import com.hr.management.sonik.controller.dto.SendUpdateStageEmailRequestDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendRegistrationEmail(SendRegistrationEmailRequestDto request) throws MessagingException;

    void sendUpdateStageEmail(SendUpdateStageEmailRequestDto request) throws MessagingException;
}

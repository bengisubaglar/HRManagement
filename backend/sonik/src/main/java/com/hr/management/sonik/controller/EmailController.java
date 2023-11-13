package com.hr.management.sonik.controller;

import com.hr.management.sonik.controller.dto.SendRegistrationEmailRequestDto;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(Constants.BASE_API_URL + "/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/sendRegistrationEmail")
    public ResponseEntity<Object> sendRegistrationEmail(@RequestBody SendRegistrationEmailRequestDto request) throws MessagingException {
        emailService.sendRegistrationEmail(request);
        return ResponseEntity.ok().build();
    }
}

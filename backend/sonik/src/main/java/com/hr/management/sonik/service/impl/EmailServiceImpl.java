package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.controller.dto.SendRegistrationEmailRequestDto;
import com.hr.management.sonik.controller.dto.SendUpdateStageEmailRequestDto;
import com.hr.management.sonik.entity.Stage;
import com.hr.management.sonik.repository.StageRepository;
import com.hr.management.sonik.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final StageRepository stageRepository;

    public void sendRegistrationEmail(SendRegistrationEmailRequestDto request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("noreplysonik@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, request.getTo());
        message.setSubject("SonIK: Registration");

        String htmlContent = "<p>Hello!</p>" +
                "<p>Thank you for your interest in joining us! We're looking forward to meeting you. In order to proceed first stage, we're waiting for you to sign up our system using the link below.</p>" +
                "<a href=http://localhost:4200/> sonIK: Registration.</a>" +
                "<p></p>" +
                "<p></p>" +
                "<p>Best Regards,</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    @Override
    public void sendUpdateStageEmail(SendUpdateStageEmailRequestDto request) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("noreplysonik@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, request.getTo());
        message.setSubject("SonIK: Status Update");

        Optional<Stage> stage = stageRepository.findById(request.getStageId() < 4 ? request.getStageId() + 1 : request.getStageId());

        if (stage.isEmpty()) {
            throw new RuntimeException("Invalid Stage!");
        }

        if (request.getPassed()) {
            if (request.getStageId() > 0 && request.getStageId() < 3) {
                String htmlContent = "<p>Hello!</p>" +
                        "<p>We're pleased to inform you that you've successfully passed the stage. For your next stage, you're expected to attend the next test. Information about the test is given below. Please, don't forget to attend your test on time.</p>" +
                        "<p> Stage: " +  stage.get().getStageName() + "</p>" +
                        "<p> Start Date: " +  request.getStartDate() + "</p>" +
                        "<p> End Date: " +  request.getEndDate() + "</p>" +
                        "<p>Best Regards,</p>";
                message.setContent(htmlContent, "text/html; charset=utf-8");
            } else {
                String htmlContent = "<p>Hello!</p>" +
                        "<p>We're pleased to inform you that you've successfully passed the stage. We'll be in touch with you again soon.</p>" +
                        "<p></p>" +
                        "<p>Best Regards,</p>";
                message.setContent(htmlContent, "text/html; charset=utf-8");
            }
        } else {
            String htmlContent = "<p>Hello!</p>" +
                    "<p>We're sorry to inform you that we're decided to continue without you. Thank you for your interest in our company. We wish you the best of luck in your career</p>" +
                    "<p></p>" +
                    "<p>Best Regards,</p>";
            message.setContent(htmlContent, "text/html; charset=utf-8");
        }

        mailSender.send(message);
    }
}

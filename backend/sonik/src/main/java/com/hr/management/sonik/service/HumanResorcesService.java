package com.hr.management.sonik.service;

import com.hr.management.sonik.controller.dto.UpdateInterviewRequestDto;
import com.hr.management.sonik.dto.HumanResourcesDto;
import com.hr.management.sonik.dto.candidate.CandidateDto;
import jakarta.mail.MessagingException;

import java.util.List;

public interface HumanResorcesService {

    HumanResourcesDto findByEmail(String email);
    List<CandidateDto> findCandidatesByEmail(String email);

    void updateInterview(UpdateInterviewRequestDto requestDto) throws MessagingException;
    List<HumanResourcesDto> findAll();
}

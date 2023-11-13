package com.hr.management.sonik.service;

import com.hr.management.sonik.controller.dto.CandidateUpdateRequest;
import com.hr.management.sonik.controller.dto.TestConfirmationRequestDto;
import com.hr.management.sonik.controller.dto.UpdateStageStatusRequestDto;
import com.hr.management.sonik.dto.candidate.CandidateDto;
import com.hr.management.sonik.dto.candidate.GetAssociatedHrResponseDto;
import com.hr.management.sonik.dto.candidate.TestConfirmationResponseDto;
import com.hr.management.sonik.entity.Candidate;
import jakarta.mail.MessagingException;

import java.util.Optional;

public interface CandidateService {

    CandidateDto findByEmail(String email);

    Boolean isAlreadyRegistered(String email);

    Optional<Candidate> findById(Long id);

    CandidateDto updateCandidate(CandidateUpdateRequest request);

    void assignFirstStage(Candidate candidate);

    void updateStageStatus(UpdateStageStatusRequestDto requestDto) throws MessagingException;

    void delete(Long id);

    TestConfirmationResponseDto testConfirmation(TestConfirmationRequestDto request);
    GetAssociatedHrResponseDto getAssociatedHr(String email);
}

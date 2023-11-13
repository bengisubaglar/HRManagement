package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.controller.dto.SendUpdateStageEmailRequestDto;
import com.hr.management.sonik.controller.dto.UpdateInterviewRequestDto;
import com.hr.management.sonik.dto.HumanResourcesDto;
import com.hr.management.sonik.dto.candidate.CandidateDto;
import com.hr.management.sonik.dto.enums.StageStates;
import com.hr.management.sonik.entity.*;
import com.hr.management.sonik.mapper.CandidateMapper;
import com.hr.management.sonik.mapper.HumanResourcesMapper;
import com.hr.management.sonik.repository.CandidateRepository;
import com.hr.management.sonik.repository.HumanResourcesRepository;
import com.hr.management.sonik.repository.StageRepository;
import com.hr.management.sonik.service.EmailService;
import com.hr.management.sonik.service.HumanResorcesService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HumanResourcesServiceImpl implements HumanResorcesService {

    private final HumanResourcesRepository humanResourcesRepository;
    private final CandidateRepository candidateRepository;
    private final StageRepository stageRepository;
    private final EmailService emailService;

    @Override
    public HumanResourcesDto findByEmail(String email) {
        HumanResources humanResources = humanResourcesRepository.findByEmail(email).orElseThrow();

        return HumanResourcesMapper.toHumanResourcesDto(humanResources);
    }
    @Override
    public List<CandidateDto> findCandidatesByEmail(String email) {
        Optional<HumanResources> humanResources = humanResourcesRepository.findByEmail(email);

        return humanResources.map(resources -> CandidateMapper.toCandidateDto(List.copyOf(resources.getCandidates()))).orElse(null);
    }

    @Override
    public void updateInterview(UpdateInterviewRequestDto requestDto) throws MessagingException {
        HumanResources hr = humanResourcesRepository.findById(requestDto.getHrId()).orElseThrow();

        Candidate candidate = hr.getCandidates().stream()
                .filter(c -> Objects.equals(c.getEmail(), requestDto.getCandidateEmail())).findAny().orElseThrow();

        if (requestDto.getForceUpdate()) {
            candidate.getCandidateStages().stream()
                    .filter(stage -> stage.getStage().getId().equals(3L))
                    .findAny().orElseThrow().setState(requestDto.getIsPassed() ? StageStates.COMPLETED : StageStates.FAILED
                    );

            if (requestDto.getIsPassed()) {
                assignFinalStage(candidate);
            }

            candidateRepository.save(candidate);
            sendInterviewResultEmail(requestDto);
            return;
        }

        candidate.getCandidateStages().stream()
                .filter(stage -> stage.getStage().getId().equals(3L))
                .filter(stage -> stage.getState().equals(StageStates.ONGOING))
                .findAny().orElseThrow().setState(requestDto.getIsPassed() ? StageStates.COMPLETED : StageStates.FAILED
                );

        if (requestDto.getIsPassed()) {
            assignFinalStage(candidate);
        }

        candidateRepository.save(candidate);
        sendInterviewResultEmail(requestDto);
    }

    @Override
    public List<HumanResourcesDto> findAll() {
        List<HumanResources> humanResources = humanResourcesRepository.findAll();
        return HumanResourcesMapper.toHumanResourcesDto(humanResources);
    }

    public void assignFinalStage(Candidate candidate) {
        CandidateStage candidateStage = CandidateStage.builder()
                .state(StageStates.ONGOING)
                .stage(stageRepository.findById(4L).orElseThrow())
                .candidateTest(null)
                .build();

        candidate.addCandidateStage(candidateStage);
    }

    public void sendInterviewResultEmail(UpdateInterviewRequestDto requestDto) throws MessagingException {
        SendUpdateStageEmailRequestDto emailRequestDto = SendUpdateStageEmailRequestDto.builder()
                .to(requestDto.getCandidateEmail())
                .stageId(3L)
                .passed(requestDto.getIsPassed())
                .build();

        emailService.sendUpdateStageEmail(emailRequestDto);
    }
}

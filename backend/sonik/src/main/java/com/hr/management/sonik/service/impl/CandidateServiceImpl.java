package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.controller.dto.CandidateUpdateRequest;
import com.hr.management.sonik.controller.dto.SendUpdateStageEmailRequestDto;
import com.hr.management.sonik.controller.dto.TestConfirmationRequestDto;
import com.hr.management.sonik.controller.dto.UpdateStageStatusRequestDto;
import com.hr.management.sonik.dto.candidate.CandidateDto;
import com.hr.management.sonik.dto.candidate.GetAssociatedHrResponseDto;
import com.hr.management.sonik.dto.candidate.TestConfirmationResponseDto;
import com.hr.management.sonik.dto.enums.StageStates;
import com.hr.management.sonik.entity.Candidate;
import com.hr.management.sonik.entity.CandidateStage;
import com.hr.management.sonik.entity.CandidateTest;
import com.hr.management.sonik.entity.HumanResources;
import com.hr.management.sonik.mapper.CandidateMapper;
import com.hr.management.sonik.repository.CandidateRepository;
import com.hr.management.sonik.repository.StageRepository;
import com.hr.management.sonik.repository.TestRepository;
import com.hr.management.sonik.service.CandidateService;
import com.hr.management.sonik.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final StageRepository stageRepository;
    private final TestRepository testRepository;
    private final EmailService emailService;

    @Override
    public CandidateDto findByEmail(String email) {
        Candidate candidate = candidateRepository.findUserByEmail(email).orElseThrow();

        List<CandidateStage> candidateStages = candidate.getCandidateStages();

        Collections.sort(candidateStages, Comparator.comparing(CandidateStage::getId));
        candidate.setCandidateStages(candidateStages);

        return CandidateMapper.toCandidateDto(candidate);

    }

    @Override
    public Boolean isAlreadyRegistered(String email) {
        Optional<Candidate> candidate = candidateRepository.findUserByEmail(email);

        return candidate.isPresent();
    }

    @Override
    public CandidateDto updateCandidate(CandidateUpdateRequest request) {

        Optional<Candidate> candidate = candidateRepository.findUserByEmail(request.getEmail());
        candidate.get().setFirstName(request.getFirstName());
        candidate.get().setLastName(request.getLastName());
        candidate.get().setTckn(request.getTckn());
        candidate.get().setGender(request.getGender());
        candidate.get().setBirthdate(request.getBirthdate());
        candidate.get().setAddress(request.getAddress());
        candidate.get().setAbout(request.getAbout());

        candidateRepository.save(candidate.get());
        return CandidateMapper.toCandidateDto(candidate.get());

    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public void assignFirstStage(Candidate candidate) {

        CandidateTest candidateTest = CandidateTest.builder()
                .duration(30)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(3))
                .test(testRepository.findById(1L).get())
                .build();

        CandidateStage firstStage = CandidateStage.builder()
                .state(StageStates.ONGOING)
                .stage(stageRepository.findById(1L).get())
                .candidateTest(candidateTest)
                .build();

        candidate.addCandidateStage(firstStage);
    }

    @Override
    public void updateStageStatus(UpdateStageStatusRequestDto requestDto) throws MessagingException {

        Optional<Candidate> candidate = candidateRepository.findById(requestDto.getId());

        if (candidate.isEmpty()) {
            throw new RuntimeException("Candidate Not Found Exception");
        }

        Boolean hasCandidateStage = candidate.get().getCandidateStages().stream()
                .anyMatch(stage -> Objects.equals(stage.getStage().getId(), requestDto.getStageId()));
        Boolean hasScore = requestDto.getScore() != null;

        if (candidate.get().getCandidateStages().stream().filter(stage -> !stage.getState().equals(StageStates.ONGOING))
                .filter(stage -> stage.getStage().getId().equals(requestDto.getStageId())).count() >= 1) {
            throw new RuntimeException("Candidate already registered to stage!");
        }


        if (hasCandidateStage && candidate.get().getCandidateStages().stream().anyMatch(stage -> Objects.equals(stage.getState(),StageStates.ONGOING))) {
            if (hasScore) {
                if (requestDto.getScore() >= 60L && requestDto.getScore() <= 100L ) {
                    candidate.get().getCandidateStages().stream().filter(stage -> stage.getStage().getId().equals(requestDto.getStageId()))
                            .findFirst().get().setState(StageStates.COMPLETED);
                    candidate.get().getCandidateStages().stream().filter(stage -> stage.getStage().getId().equals(requestDto.getStageId()))
                            .findFirst().get().getCandidateTest().setScore(requestDto.getScore().toString());
                }

                if (requestDto.getScore() >= 0L && requestDto.getScore() < 60L) {
                    candidate.get().getCandidateStages().stream().filter(stage -> stage.getStage().getId().equals(requestDto.getStageId()))
                            .findFirst().get().setState(StageStates.FAILED);
                    candidate.get().getCandidateStages().stream().filter(stage -> stage.getStage().getId().equals(requestDto.getStageId()))
                            .findFirst().get().getCandidateTest().setScore(requestDto.getScore().toString());
                }

                if (requestDto.getPassed()) {
                    CandidateTest candidateTest = CandidateTest.builder()
                            .duration(30)
                            .startDate(LocalDateTime.now())
                            .endDate(LocalDateTime.now().plusDays(3))
                            .test(testRepository.findById(requestDto.getStageId() + 1).get())
                            .build();

                    CandidateStage stage = CandidateStage.builder()
                            .state(StageStates.ONGOING)
                            .stage(stageRepository.findById(requestDto.getStageId() + 1).get())
                            .candidateTest(candidateTest)
                            .build();

                    candidateTest.setStage(stage);

                    candidate.get().addCandidateStage(stage);
                }

                candidateRepository.save(candidate.get());

                SendUpdateStageEmailRequestDto emailRequest = SendUpdateStageEmailRequestDto.builder()
                        .passed(requestDto.getPassed())
                        .stageId(requestDto.getStageId())
                        .to(candidate.get().getEmail())
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(3))
                        .build();

                emailService.sendUpdateStageEmail(emailRequest);

            } else {
                candidate.get().getCandidateStages().stream().filter(stage -> stage.getStage().getId().equals(requestDto.getStageId()))
                        .findFirst().get().setState(requestDto.getPassed() ? StageStates.COMPLETED : StageStates.FAILED);

                if (requestDto.getStageId() == 3 && requestDto.getPassed()) {
                    CandidateStage stage = CandidateStage.builder()
                            .state(StageStates.ONGOING)
                            .stage(stageRepository.findById(requestDto.getStageId() + 1).get())
                            .candidateTest(null)
                            .build();

                    candidate.get().addCandidateStage(stage);
                }

                candidateRepository.save(candidate.get());

                SendUpdateStageEmailRequestDto emailRequest = SendUpdateStageEmailRequestDto.builder()
                        .passed(requestDto.getPassed())
                        .stageId(requestDto.getStageId())
                        .to(candidate.get().getEmail())
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(3))
                        .build();

                emailService.sendUpdateStageEmail(emailRequest);
            }
        } else {
            throw new RuntimeException("Candidate Stage Not Found!");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if (candidate.isEmpty()) {
            throw new RuntimeException("Candidate Not Found");
        }

        candidateRepository.delete(candidate.get());
    }

    @Override
    public TestConfirmationResponseDto testConfirmation(TestConfirmationRequestDto requestDto) {
        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId()).orElse(null);

        if (candidate == null) {
            throw new RuntimeException("Candidate Not Found Exception");
        }

        CandidateTest candidateTest = candidate.getCandidateStages().stream()
                .filter(stage -> Objects.equals(stage.getStage().getId(), requestDto.getStageId()))
                .filter(stage -> Objects.equals(stage.getState(), StageStates.ONGOING))
                .findFirst().orElseThrow().getCandidateTest();

        Integer duration = candidateTest.getDuration();

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(candidateTest.getStartDate()) || now.isAfter(candidateTest.getEndDate())) {
            throw new RuntimeException("Invalid Test Date");
        }

        TestConfirmationResponseDto responseDto = new TestConfirmationResponseDto();
        responseDto.setTest(candidateTest.getTest());
        responseDto.setDuration(duration);
        return responseDto;
    }

    @Override
    public GetAssociatedHrResponseDto getAssociatedHr(String email) {

        Candidate candidate = candidateRepository.findUserByEmail(email).orElseThrow();
        HumanResources humanResources = candidate.getHumanResources();

        GetAssociatedHrResponseDto responseDto = new GetAssociatedHrResponseDto();
        responseDto.setEmail(humanResources.getEmail());
        responseDto.setFirstName(humanResources.getFirstName());
        responseDto.setLastName(humanResources.getLastName());

        return responseDto;
    }
}

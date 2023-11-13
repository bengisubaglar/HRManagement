package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.dto.StageDto;
import com.hr.management.sonik.entity.CandidateStage;
import com.hr.management.sonik.mapper.StageMapper;
import com.hr.management.sonik.repository.CandidateStageRepository;
import com.hr.management.sonik.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService {

    private final CandidateStageRepository candidateStageRepository;

    @Override
    public StageDto findById(Long id) {
        Optional<CandidateStage> stage = candidateStageRepository.findById(id);

        return stage.map(StageMapper::toStageDto).orElse(null);
    }
}

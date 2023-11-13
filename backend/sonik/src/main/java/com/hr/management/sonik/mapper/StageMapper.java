package com.hr.management.sonik.mapper;

import com.hr.management.sonik.dto.StageDto;
import com.hr.management.sonik.entity.CandidateStage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StageMapper {

    public static StageDto toStageDto(CandidateStage candidateStage) {

        return StageDto.builder()
                .id(candidateStage.getId())
                .state(candidateStage.getState())
                .stageId(candidateStage.getStage().getId())
                .candidates(candidateStage.getCandidates())
                .build();
    }

    public static List<StageDto> toStageDto(List<CandidateStage> candidateStages) {

        return candidateStages.stream().map(StageMapper::toStageDto).collect(Collectors.toList());
    }
}

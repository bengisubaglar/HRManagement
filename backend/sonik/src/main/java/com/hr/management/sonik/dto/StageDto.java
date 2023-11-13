package com.hr.management.sonik.dto;

import com.hr.management.sonik.dto.enums.StageStates;
import com.hr.management.sonik.entity.Candidate;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StageDto {

    private Long id;
    private Long testId;
    private StageStates state;
    private Long stageId;
    private List<Candidate> candidates;
}

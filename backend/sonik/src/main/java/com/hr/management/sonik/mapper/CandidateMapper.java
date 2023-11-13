package com.hr.management.sonik.mapper;


import com.hr.management.sonik.dto.candidate.CandidateDto;
import com.hr.management.sonik.entity.Candidate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateMapper {

    public static CandidateDto toCandidateDto(Candidate candidate) {

        return CandidateDto.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .gender(candidate.getGender())
                .birthdate(candidate.getBirthdate())
                .tckn(candidate.getTckn())
                .address(candidate.getAddress())
                .userRole(candidate.getUserRole())
                .isActive(candidate.getIsActive())
                .creationDate(candidate.getCreationDate())
                .candidateStages(candidate.getCandidateStages())
                .about(candidate.getAbout())
                .build();
    }

    public static List<CandidateDto> toCandidateDto(List<Candidate> candidate) {
        return candidate.stream().map(CandidateMapper::toCandidateDto).collect(Collectors.toList());
    }


}

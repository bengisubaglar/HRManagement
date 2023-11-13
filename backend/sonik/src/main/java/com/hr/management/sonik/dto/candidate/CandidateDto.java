package com.hr.management.sonik.dto.candidate;

import com.hr.management.sonik.dto.enums.Gender;
import com.hr.management.sonik.dto.enums.UserRole;
import com.hr.management.sonik.entity.CandidateStage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CandidateDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthdate;
    private String tckn;
    private String address;
    private UserRole userRole;
    private String isActive;
    private LocalDateTime creationDate;
    private LocalDateTime lastLogin;
    private List<CandidateStage> candidateStages;
    private String about;
}

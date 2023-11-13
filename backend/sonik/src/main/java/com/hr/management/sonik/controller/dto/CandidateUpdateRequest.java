package com.hr.management.sonik.controller.dto;

import com.hr.management.sonik.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateUpdateRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String tckn;
    private String address;
    private Gender gender;
    private LocalDate birthdate;
    private String about;


}

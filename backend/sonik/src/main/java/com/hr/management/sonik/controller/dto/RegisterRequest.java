package com.hr.management.sonik.controller.dto;

import com.hr.management.sonik.dto.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String tckn;
    private UserRole userRole;
}

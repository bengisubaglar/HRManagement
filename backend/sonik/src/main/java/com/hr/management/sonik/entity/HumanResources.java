package com.hr.management.sonik.entity;

import com.hr.management.sonik.dto.enums.Gender;
import com.hr.management.sonik.dto.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "HUMAN_RESOURCES")
public class HumanResources extends BaseUser {

    @OneToMany(mappedBy = "humanResources", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    @Builder
    public HumanResources(
            Long id,
            String email,
            String password,
            String firstName,
            String lastName,
            Gender gender,
            LocalDate birthdate,
            String tckn,
            String address,
            UserRole userRole,
            String isActive,
            LocalDateTime creationDate,
            LocalDateTime lastLogin,
            List<Candidate> candidates) {
        super(id, email, password, firstName, lastName, gender, birthdate, tckn, address,
                userRole, isActive, creationDate, lastLogin);
        this.candidates = candidates;
    }
}

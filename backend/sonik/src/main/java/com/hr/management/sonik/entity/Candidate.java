package com.hr.management.sonik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hr.management.sonik.dto.enums.Gender;
import com.hr.management.sonik.dto.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "CANDIDATE")
public class Candidate extends BaseUser {
    @Column(name="ABOUT")
    private String about;

    @ManyToOne
    @JoinColumn(name="HUMAN_RESOURCES_ID")
    @JsonIgnore
    private HumanResources humanResources;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(name = "AT_STAGE",
            joinColumns = { @JoinColumn(name = "CANDIDATE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CANDIDATE_STAGE_ID") })
    private List<CandidateStage> candidateStages = new ArrayList<>();


    @Builder
    public Candidate(
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
            HumanResources humanResources,
            String about) {
        super(id, email, password, firstName, lastName, gender, birthdate, tckn, address,
                userRole, isActive, creationDate, lastLogin);
        this.humanResources = humanResources;
        this.about = about;
    }

    public void assignHumanResources(HumanResources humanResources) {
        this.setHumanResources(humanResources);
        humanResources.getCandidates().add(this);
    }

    public void addCandidateStage(CandidateStage candidateStage) {
        this.candidateStages.add(candidateStage);
        candidateStage.setCandidates(new ArrayList<>());
        candidateStage.getCandidates().add(this);
    }

    public void removeStage(Long stageId) {
        CandidateStage candidateStage = this.candidateStages.stream().filter(t -> Objects.equals(t.getId(), stageId)).findFirst().orElse(null);
        if (candidateStage != null) {
            this.candidateStages.remove(candidateStage);
            candidateStage.getCandidates().remove(this);
        }
    }
}

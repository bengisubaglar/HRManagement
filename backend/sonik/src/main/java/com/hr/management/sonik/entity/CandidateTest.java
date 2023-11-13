package com.hr.management.sonik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "CANDIDATE_TEST")
public class CandidateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DURATION")
    private Integer duration;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "SCORE")
    private String score;

    @OneToOne(mappedBy = "candidateTest", cascade = CascadeType.ALL)
    @JsonIgnore
    private CandidateStage stage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_ID", referencedColumnName = "ID")
    private Test test;

}

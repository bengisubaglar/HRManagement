package com.hr.management.sonik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hr.management.sonik.dto.enums.StageStates;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "CANDIDATE_STAGE")
public class CandidateStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private StageStates state;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.ALL
            },
            mappedBy = "candidateStages")
    @JsonIgnore
    private List<Candidate> candidates = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_ID", referencedColumnName = "ID")
    private CandidateTest candidateTest;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STAGE_ID", referencedColumnName = "ID")
    private Stage stage;
}

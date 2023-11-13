package com.hr.management.sonik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "TEST")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String testName;

    @OneToOne(mappedBy = "test",cascade = CascadeType.ALL)
    @JsonIgnore
    private CandidateTest candidateTest;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Question> questions;
}

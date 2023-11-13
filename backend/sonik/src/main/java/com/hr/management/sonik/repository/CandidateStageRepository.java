package com.hr.management.sonik.repository;

import com.hr.management.sonik.entity.CandidateStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateStageRepository extends JpaRepository<CandidateStage, Long> {
}

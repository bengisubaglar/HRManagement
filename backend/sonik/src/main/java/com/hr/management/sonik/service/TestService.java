package com.hr.management.sonik.service;

import com.hr.management.sonik.entity.CandidateTest;

import java.util.Optional;

public interface TestService {

    Optional<CandidateTest> findById(Long id);
}

package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.entity.CandidateTest;
import com.hr.management.sonik.repository.CandidateTestRepository;
import com.hr.management.sonik.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final CandidateTestRepository candidateTestRepository;

    public Optional<CandidateTest> findById(Long id) {
        return candidateTestRepository.findById(id);
    }

}

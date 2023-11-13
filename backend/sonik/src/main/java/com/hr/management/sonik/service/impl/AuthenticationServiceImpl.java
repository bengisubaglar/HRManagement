package com.hr.management.sonik.service.impl;

import com.hr.management.sonik.config.JwtService;
import com.hr.management.sonik.controller.dto.AuthenticationRequest;
import com.hr.management.sonik.controller.dto.AuthenticationResponse;
import com.hr.management.sonik.controller.dto.RegisterRequest;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.dto.enums.UserRole;
import com.hr.management.sonik.entity.Candidate;
import com.hr.management.sonik.entity.HumanResources;
import com.hr.management.sonik.repository.CandidateRepository;
import com.hr.management.sonik.repository.HumanResourcesRepository;
import com.hr.management.sonik.service.AuthenticationService;
import com.hr.management.sonik.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CandidateRepository candidateRepository;
    private final HumanResourcesRepository humanResourcesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CandidateService candidateService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        Candidate candidate = Candidate.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthdate(request.getBirthdate())
                .tckn(request.getTckn())
                .userRole(UserRole.CANDIDATE)
                .isActive(Constants.PASSIVE)
                .creationDate(LocalDateTime.now())
                .humanResources(assignToHumanResources())
                .build();

        Boolean isAlreadyRegistered = candidateService.isAlreadyRegistered(request.getEmail());

        candidateService.assignFirstStage(candidate);

        if (isAlreadyRegistered) {
            return AuthenticationResponse.builder()
                    .errorMsg("ERROR: User is already registered!")
                    .build();
        }

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userRole",candidate.getUserRole());

        candidateRepository.save(candidate);
        var jwtToken = jwtService.generateToken(extraClaims,candidate);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private HumanResources assignToHumanResources() {
        List<HumanResources> humanResourcesList = humanResourcesRepository.findAll();

        int length = humanResourcesList.size();
        int randomHrIndex = new Random().nextInt(length);

        return humanResourcesList.isEmpty() ? null : humanResourcesList.get(randomHrIndex);
    }


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        Candidate candidate = candidateRepository.findUserByEmail(request.getEmail())
                .orElseThrow();

        if (Objects.equals(candidate.getIsActive(),Constants.PASSIVE) && Objects.isNull(candidate.getLastLogin())) {
            candidate.setIsActive(Constants.ACTIVE);
        }

        if (Objects.equals(candidate.getIsActive(),Constants.PASSIVE)) {
            return AuthenticationResponse.builder().errorMsg("Account must be activated!").build();
        }

        candidate.setLastLogin(LocalDateTime.now());
        candidateRepository.save(candidate);

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userRole", candidate.getUserRole());
        extraClaims.put("id", candidate.getId());

        String jwtToken = jwtService.generateToken(extraClaims ,candidate);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse hrLogin(AuthenticationRequest request) {
        HumanResources hr = humanResourcesRepository.findByEmail(request.getEmail())
                .orElseThrow();

        if (Objects.equals(hr.getIsActive(),Constants.PASSIVE) && Objects.isNull(hr.getLastLogin())) {
            hr.setIsActive(Constants.ACTIVE);
        }

        if (Objects.equals(hr.getIsActive(),Constants.PASSIVE)) {
            return AuthenticationResponse.builder().errorMsg("Account must be activated!").build();
        }

        hr.setLastLogin(LocalDateTime.now());
        humanResourcesRepository.save(hr);

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userRole", hr.getUserRole());
        extraClaims.put("id", hr.getId());

        String jwtToken = jwtService.generateToken(extraClaims ,hr);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

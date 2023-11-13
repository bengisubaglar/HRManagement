package com.hr.management.sonik.service;

import com.hr.management.sonik.controller.dto.AuthenticationRequest;
import com.hr.management.sonik.controller.dto.AuthenticationResponse;
import com.hr.management.sonik.controller.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse hrLogin(AuthenticationRequest request);
}

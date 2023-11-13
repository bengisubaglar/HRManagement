package com.hr.management.sonik.controller;


import com.hr.management.sonik.controller.dto.AuthenticationRequest;
import com.hr.management.sonik.controller.dto.AuthenticationResponse;
import com.hr.management.sonik.controller.dto.RegisterRequest;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value =  Constants.BASE_API_URL + "/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/candidate/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/candidate/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/hr/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.hrLogin(request));
    }
}

package com.hr.management.sonik.controller;

import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.BASE_API_URL + "/test")
public class TestController {

    private final TestService testService;

    @GetMapping( "/findById")
    public ResponseEntity<Object> findById(@RequestParam Long id) {
        return ResponseEntity.ok().body(testService.findById(id));
    }
}

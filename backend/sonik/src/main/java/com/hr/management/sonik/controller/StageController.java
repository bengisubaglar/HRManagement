package com.hr.management.sonik.controller;

import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.BASE_API_URL + "/stage")
public class StageController {

    private final StageService stageService;

    @GetMapping( "/findById")
    public ResponseEntity<Object> findById(@RequestParam Long id) {
        return ResponseEntity.ok().body(stageService.findById(id));
    }

}

package com.hr.management.sonik.controller;

import com.hr.management.sonik.controller.dto.UpdateInterviewRequestDto;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.HumanResorcesService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = Constants.BASE_API_URL + "/hr")
public class HumanResourcesController {

    private final HumanResorcesService humanResorcesService;

    @GetMapping( "/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok().body(humanResorcesService.findByEmail(email));
    }
    @GetMapping("/candidates")
    public ResponseEntity<Object> findCandidates(@RequestParam String email){
        return ResponseEntity.ok().body(humanResorcesService.findCandidatesByEmail(email));
    }

    @PostMapping("/update-interview")
    public ResponseEntity<Object> updateInterview(@RequestBody UpdateInterviewRequestDto requestDto) throws MessagingException {
        humanResorcesService.updateInterview(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping( "/findAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(humanResorcesService.findAll());
    }
}

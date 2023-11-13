package com.hr.management.sonik.controller;


import com.hr.management.sonik.controller.dto.CandidateUpdateRequest;
import com.hr.management.sonik.controller.dto.TestConfirmationRequestDto;
import com.hr.management.sonik.controller.dto.UpdateStageStatusRequestDto;
import com.hr.management.sonik.dto.constants.Constants;
import com.hr.management.sonik.service.CandidateService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = Constants.BASE_API_URL + "/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok().body(candidateService.findByEmail(email));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody CandidateUpdateRequest request){
       return ResponseEntity.ok().body(candidateService.updateCandidate(request));
    }

    @GetMapping("/getStages")
    public ResponseEntity<Object> getStages(@RequestParam String email) {
        return ResponseEntity.ok().body(candidateService.findByEmail(email).getCandidateStages());
    }

    @PostMapping("/updateCandidateStage")
    public ResponseEntity<Object> updateCandidateStage(@RequestBody UpdateStageStatusRequestDto request) throws MessagingException {
        candidateService.updateStageStatus(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long id){
        candidateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/testConfirmation")
    public ResponseEntity<Object> testConfirmation(@RequestBody TestConfirmationRequestDto request) {
        return ResponseEntity.ok().body(candidateService.testConfirmation(request));
    }

    @GetMapping("/getAssociatedHr")
    public ResponseEntity<Object> getAssociatedHr(@RequestParam String email) {
        return ResponseEntity.ok().body(candidateService.getAssociatedHr(email));
    }
}

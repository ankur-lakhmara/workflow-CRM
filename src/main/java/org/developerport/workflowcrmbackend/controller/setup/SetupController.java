package org.developerport.workflowcrmbackend.controller.setup;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.setup.SetupRequest;
import org.developerport.workflowcrmbackend.service.SetupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class SetupController {
    private final SetupService setupService;
    @PostMapping("/setup")
    public ResponseEntity<String> setup(@RequestBody SetupRequest request){
        setupService.bootstrap(request);
        return ResponseEntity.ok("System Setup done!");
    }
}

package org.developerport.workflowcrmbackend.controller.lead;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.lead.LeadCreationRequest;
import org.developerport.workflowcrmbackend.service.lead.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lead")
public class leadController {

    private final LeadService leadService;

    @PostMapping("/create")
    public ResponseEntity<String> createLead(@RequestBody LeadCreationRequest req, HttpServletRequest httpRequest){
        Integer tenantId = (Integer) httpRequest.getAttribute("tenantId");
        leadService.createLead(req, tenantId);
        return ResponseEntity.ok("Lead Created Successfully ");
    }
}

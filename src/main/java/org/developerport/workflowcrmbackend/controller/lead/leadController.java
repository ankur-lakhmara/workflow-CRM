package org.developerport.workflowcrmbackend.controller.lead;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.lead.LeadCreationRequest;
import org.developerport.workflowcrmbackend.service.lead.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lead")
public class leadController {

    private final LeadService leadService;

    @PostMapping("/create")
    public ResponseEntity<String> createLead(@RequestBody LeadCreationRequest req, HttpServletRequest httpRequest){
        Integer tenantId = (Integer) httpRequest.getAttribute("tenantId");
        String role = (String) httpRequest.getAttribute("role");
        leadService.createLead(req, tenantId,role);
        return ResponseEntity.ok("Lead Created Successfully ");
    }

    @PostMapping("/{leadId}/assign/{userId}")
    public ResponseEntity<String> assignLead(@PathVariable Integer leadId, @PathVariable Integer userId, HttpServletRequest req){
        Integer tenantId = (Integer) req.getAttribute("tenantId");
        String role = (String) req.getAttribute("role");
        leadService.assignLead(leadId,userId,tenantId,role);
        return ResponseEntity.ok("Lead assigned successfully");
    }
}

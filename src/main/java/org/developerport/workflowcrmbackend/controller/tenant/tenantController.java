package org.developerport.workflowcrmbackend.controller.tenant;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.tenant.createTenantRequest;
import org.developerport.workflowcrmbackend.dto.tenant.getAllTenantResponse;
import org.developerport.workflowcrmbackend.service.tenant.tenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tenant")
public class tenantController {
    private final tenantService tenantService;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody createTenantRequest req, HttpServletRequest httprequest){
        String role = (String) httprequest.getAttribute("role");
        tenantService.createTenant(req.getTenantName(),role);
        return ResponseEntity.ok("Tenant created");
    }
    @GetMapping()
    public ResponseEntity<List<getAllTenantResponse>> getAllTenants(HttpServletRequest httprequest){
        String role = (String) httprequest.getAttribute("role");
       return ResponseEntity.ok( tenantService.getAllTenants(role));
    }
}

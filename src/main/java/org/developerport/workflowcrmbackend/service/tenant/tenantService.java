package org.developerport.workflowcrmbackend.service.tenant;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.tenant.getAllTenantResponse;
import org.developerport.workflowcrmbackend.model.tenant.Status;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;



@Service
@RequiredArgsConstructor
public class tenantService {
    private final TenantRepository tenantRepository;

    @Transactional
    public List<getAllTenantResponse> getAllTenants(String role){
        if(!role.equals("SUPER_ADMIN")){
            throw new RuntimeException("Only super admin can see the list of tenants");
        }
        return tenantRepository.findAll()
                .stream()
                .map(t-> new getAllTenantResponse(t.getId(),t.getName()))
                .toList();
    }
    @Transactional
    public void createTenant(String name, String role){
        if(!role.equals("SUPER_ADMIN")){
            throw new RuntimeException("Only super admin can create tenants");
        }
        if(tenantRepository.existsByName(name)){
            throw new RuntimeException("Tenant with this name already exists");
        }

        TenantEntity tenant = new TenantEntity();
        tenant.setName(name);
        tenant.setStatus(Status.ACTIVE);
        tenant.setCreated_at(new Date());
        tenantRepository.save(tenant);
    }
}

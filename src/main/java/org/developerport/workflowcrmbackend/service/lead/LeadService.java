package org.developerport.workflowcrmbackend.service.lead;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.lead.LeadCreationRequest;
import org.developerport.workflowcrmbackend.model.lead.LeadEntity;
import org.developerport.workflowcrmbackend.model.lead.LeadSource;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.repository.LeadRepository;
import org.developerport.workflowcrmbackend.repository.TenantRepository;
import org.developerport.workflowcrmbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createLead(LeadCreationRequest req, Integer tenantId){
        System.out.println("reached step 1");
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()->new RuntimeException("tenant not found"));
        System.out.println("My tenant is "+tenant);
        LeadEntity lead = new LeadEntity();
        lead.setName(req.getName());
        lead.setSource(LeadSource.WEB);
        lead.setTenant(tenant);
        lead.setCreated_at(new Date());
        System.out.println("assignedUserId = " + req.getAssignedUserId());
        if(req.getAssignedUserId()!=null){
            UserEntity user = userRepository.
                    findById(req.getAssignedUserId())
                    .orElseThrow(()->new RuntimeException("Assigned User not found "));
            if(user.getTenant().getId() != tenantId){
                throw new RuntimeException("User doesn't belongs to this tenant");
            }
            lead.setAssignedUserId(user);
        }
        leadRepository.save(lead);

    }

}

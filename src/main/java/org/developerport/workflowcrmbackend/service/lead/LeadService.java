package org.developerport.workflowcrmbackend.service.lead;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.lead.LeadCreationRequest;
import org.developerport.workflowcrmbackend.model.lead.LeadEntity;
import org.developerport.workflowcrmbackend.model.lead.LeadSource;
import org.developerport.workflowcrmbackend.model.lead.LeadStatus;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.repository.LeadRepository;
import org.developerport.workflowcrmbackend.repository.TenantRepository;
import org.developerport.workflowcrmbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Inet4Address;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createLead(LeadCreationRequest req, Integer tenantId, String role){
        TenantEntity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()->new RuntimeException("tenant not found"));
//        System.out.println("My tenant is "+tenant);
        if(!role.equals("MANAGER") && !role.equals("ADMIN")){
            throw new RuntimeException("You are not allowed to create lead");
        }
        LeadEntity lead = new LeadEntity();
        lead.setName(req.getName());
        lead.setSource(LeadSource.WEB);
        lead.setTenant(tenant);
        lead.setCreated_at(new Date());
//        System.out.println("assignedUserId = " + req.getAssignedUserId());
//        if(req.getAssignedUserId()!=null){
//            UserEntity user = userRepository.
//                    findById(req.getAssignedUserId())
//                    .orElseThrow(()->new RuntimeException("Assigned User not found "));
//            if(user.getTenant().getId() != tenantId){
//                throw new RuntimeException("User doesn't belongs to this tenant");
//            }
//            lead.setAssignedUserId(user);
//        }
        System.out.println(lead);
        leadRepository.save(lead);
    }

    @Transactional
    public void assignLead(Integer leadId, Integer userId, Integer tenantId, String role){
        //role validation ...
        if(!role.equals("ADMIN") && !role.equals("MANAGER")){
            throw new RuntimeException("Not allowed to assign lead");
        }
        //fetching lead that try to assign
        LeadEntity lead = leadRepository.
                findByIdAndTenantId(leadId,tenantId)
                .orElseThrow(()->new RuntimeException("Lead not found"));

//        System.out.println("this is lead"+lead.getAssignedUserId());
        //checking is the lead is already assigned to any user or not
        if(lead.getAssignedUserId() != null){
            throw new RuntimeException("Lead is already assigned ");
        }
        //fetch the user try to assign
        UserEntity user = userRepository
                .findByIdAndTenantId(userId, tenantId)
                .orElseThrow(()->new RuntimeException("User not found"));

        //assign the lead
        lead.setAssignedUserId(user);
        lead.setStatus(LeadStatus.NEW);
        leadRepository.save(lead);
    }

//    @Transactional
//    public void UpdateStatus(Integer leadId, Integer user)
}

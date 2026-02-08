package org.developerport.workflowcrmbackend.service;


import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.setup.SetupRequest;
import org.developerport.workflowcrmbackend.model.tenant.Status;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.model.user.UserRole;
import org.developerport.workflowcrmbackend.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SetupService {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void bootstrap(SetupRequest req){
        if(userRepository.existsByName(req.getTenantName())){
            throw new RuntimeException("Tenant already Exists");
        }
//        if(userRepository.existsByEmail(req.getAdminEmail())){
//            throw new RuntimeException("Email already exists");
//        }


        TenantEntity tenant = new TenantEntity();
        tenant.setName(req.getTenantName());
        tenant.setStatus(Status.ACTIVE);
        tenant.setCreated_at(new Date());

        tenantRepository.save(tenant);

        UserEntity user = new UserEntity();
        user.setName(req.getAdminName());
        user.setEmail(req.getAdminEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(UserRole.SUPER_ADMIN);
        user.setTenant(tenant);
        user.setCreated_at(new Date());
        userRepository.save(user);
    }
}

package org.developerport.workflowcrmbackend.service.user;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.Auth.ChangePasswordRequest;
import org.developerport.workflowcrmbackend.dto.user.CreateUserRequest;
import org.developerport.workflowcrmbackend.dto.user.createAdminRequest;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.model.user.UserRole;
import org.developerport.workflowcrmbackend.repository.TenantRepository;
import org.developerport.workflowcrmbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public void createUser(CreateUserRequest req, Integer tenantId, String callerRole){
        if (callerRole.equals("ADMIN") && req.getRole() == UserRole.ADMIN) {
            throw new RuntimeException("Admin can't create another admin");
        }
        if(callerRole.equals("ADMIN") && req.getRole() == UserRole.SUPER_ADMIN){
            throw new RuntimeException("Admin can't create super admin");
        }
        TenantEntity  tenant = tenantRepository.findById(tenantId).orElseThrow();
        UserEntity user = new UserEntity();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        user.setTenant(tenant);
        user.setCreated_at(new Date());
        userRepository.save(user);
    }

    @Transactional
    public void createAdmin(createAdminRequest req, String callerRole) {
        if (!callerRole.equals("SUPER_ADMIN")) {
            throw new RuntimeException("only super admin can create admins");
        }
        if (req.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("Only admins allowed with tenants");
        }
        TenantEntity tenant = tenantRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("Tenant does not exists"));

        if(userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("User already exists");
        }
        UserEntity admin = new UserEntity();
        admin.setName(req.getName());
        admin.setEmail(req.getEmail());
        admin.setRole(UserRole.ADMIN);
        admin.setTenant(tenant);
        admin.setPassword(encoder.encode(req.getPassword()));
        admin.setCreated_at(new Date());
        userRepository.save(admin);

    }

    public void changePassword(ChangePasswordRequest req, String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(!encoder.matches(req.getCurrentPassword(),user.getPassword())){
            throw new RuntimeException("Current password is incorrect");
        }
        user.setPassword(encoder.encode(req.getNewPassword()));
        user.setUpdated_at(new Date());
        userRepository.save(user);
    }
}

package org.developerport.workflowcrmbackend.repository;

import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByRole(UserRole role);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByName(String name);
    Optional<UserEntity> findByIdAndTenantId(Integer id, Integer tenantId);
}

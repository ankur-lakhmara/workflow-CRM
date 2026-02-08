package org.developerport.workflowcrmbackend.repository;

import org.developerport.workflowcrmbackend.model.lead.LeadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, Integer> {
    Optional<LeadEntity> findByIdAndTenantId(Integer id, Integer tenantId);
    Page<LeadEntity> findByTenantId(Integer id, Pageable pageable);
}

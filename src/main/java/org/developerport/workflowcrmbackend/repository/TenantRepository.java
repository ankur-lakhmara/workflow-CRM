package org.developerport.workflowcrmbackend.repository;

import org.developerport.workflowcrmbackend.model.lead.LeadEntity;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
@Repository
public interface TenantRepository extends JpaRepository<TenantEntity,Integer> {
    boolean existsByName(String name);

    List<TenantEntity> findAll();

}

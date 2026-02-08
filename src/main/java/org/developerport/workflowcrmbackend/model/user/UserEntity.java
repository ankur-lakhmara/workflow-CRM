package org.developerport.workflowcrmbackend.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Date created_at;
    private Date updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tenant_id", nullable = false)
    private TenantEntity tenant;
}


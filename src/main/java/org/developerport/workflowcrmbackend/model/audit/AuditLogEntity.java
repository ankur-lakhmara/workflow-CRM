package org.developerport.workflowcrmbackend.model.audit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Map;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String entity_name;
    private ActionStatus action;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> oldValue;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> newValue;
    private Date created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id",nullable = false)
    private TenantEntity tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity performedBy;


}

package org.developerport.workflowcrmbackend.model.workflow;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Date;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private triggerEvents events;
    private String triggerValue;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object > actionPayload;
    private Boolean isActive;
    private Date created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tenant_id",nullable = false)
    private TenantEntity tenant;
}

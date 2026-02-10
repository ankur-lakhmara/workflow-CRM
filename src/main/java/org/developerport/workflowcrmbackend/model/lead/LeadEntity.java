package org.developerport.workflowcrmbackend.model.lead;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LeadSource source;
    @Enumerated(EnumType.STRING)
    private LeadStatus status;
    private Date created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tenant_id", nullable = false)
    private TenantEntity tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assigned_to", nullable = true)
    private UserEntity assignedUserId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name= "created_by", nullable = false)
    private UserEntity created_by;

}

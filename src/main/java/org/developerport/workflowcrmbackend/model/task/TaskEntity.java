package org.developerport.workflowcrmbackend.model.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.developerport.workflowcrmbackend.model.lead.LeadEntity;
import org.developerport.workflowcrmbackend.model.tenant.TenantEntity;
import org.developerport.workflowcrmbackend.model.user.UserEntity;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private TaskType type;
    private TaskStatus status;
    private Date due_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tenant_id", nullable = false)
    private TenantEntity tenant;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private LeadEntity leadId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedUser_id",nullable = false)
    private UserEntity userId;
}

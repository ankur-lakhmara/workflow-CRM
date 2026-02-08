package org.developerport.workflowcrmbackend.model.tenant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    private Date created_at;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    private Date updated_at;
}


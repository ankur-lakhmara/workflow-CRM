package org.developerport.workflowcrmbackend.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class getAllTenantResponse {
    private int id;
    private String tenantName;
}

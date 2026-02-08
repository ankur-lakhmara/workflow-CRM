package org.developerport.workflowcrmbackend.dto.setup;

import lombok.Data;

@Data
public class SetupRequest {
    private String tenantName;
    private String adminName;
    private String adminEmail;
    private String password;
}

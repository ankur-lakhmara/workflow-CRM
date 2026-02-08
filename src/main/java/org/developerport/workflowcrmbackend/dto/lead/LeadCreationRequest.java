package org.developerport.workflowcrmbackend.dto.lead;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeadCreationRequest {
    private String name;
    private String source;
    private Integer assignedUserId;
}

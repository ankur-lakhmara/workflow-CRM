package org.developerport.workflowcrmbackend.dto.Auth;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}

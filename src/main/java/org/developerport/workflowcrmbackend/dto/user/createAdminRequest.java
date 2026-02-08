package org.developerport.workflowcrmbackend.dto.user;

import lombok.Data;
import org.developerport.workflowcrmbackend.model.user.UserRole;
@Data
public class createAdminRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private int id;
}

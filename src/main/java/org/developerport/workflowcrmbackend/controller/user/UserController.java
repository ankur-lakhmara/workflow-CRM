package org.developerport.workflowcrmbackend.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.user.CreateUserRequest;
import org.developerport.workflowcrmbackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.developerport.workflowcrmbackend.dto.user.createAdminRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request, HttpServletRequest httpRequest){
        Integer tenantId = (Integer) httpRequest.getAttribute("tenantId");
        String role = (String) httpRequest.getAttribute("role");
        userService.createUser(request,tenantId,role);
        return ResponseEntity.ok("user created");
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdmin(@RequestBody createAdminRequest request, HttpServletRequest httpreq ){
        String role = (String) httpreq.getAttribute("role");
        userService.createAdmin(request, role);
        return ResponseEntity.ok("admin created under tenant"+request.getId());
    }

}

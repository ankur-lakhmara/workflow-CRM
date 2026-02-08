package org.developerport.workflowcrmbackend.controller.Auth;

import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.Auth.LoginRequest;
import org.developerport.workflowcrmbackend.dto.Auth.LoginResponse;
import org.developerport.workflowcrmbackend.service.auth.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Login {
    private final AuthService authService;
    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest request){
        String token  = authService.login(request);
        return new LoginResponse(token);
    }
}

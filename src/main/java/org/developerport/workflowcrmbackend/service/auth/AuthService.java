package org.developerport.workflowcrmbackend.service.auth;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.developerport.workflowcrmbackend.dto.Auth.LoginRequest;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public String login(LoginRequest req){
        UserEntity user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(()->new RuntimeException("Invalid Credentials "));
        if(!encoder.matches(req.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Creds");
        }
        return jwtService.generateToken(user);
    }
}

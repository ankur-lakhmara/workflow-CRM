package org.developerport.workflowcrmbackend.controller.Auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.dto.Auth.ChangePasswordRequest;
import org.developerport.workflowcrmbackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class changePassword {
    private final UserService userService;
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest req, HttpServletRequest httpRequest){
        String email = (String) httpRequest.getAttribute("email");
            userService.changePassword(req,email);
            return ResponseEntity.ok("Password Updated");
    }
}

package org.developerport.workflowcrmbackend.service.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.developerport.workflowcrmbackend.model.user.UserEntity;
import org.developerport.workflowcrmbackend.model.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String SECRET;

    public String generateToken(UserEntity user) {
        UserRole role = user.getRole();
        JwtBuilder builder = Jwts.builder()
                    .subject(user.getEmail())
                    .claim("role", role)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()));

        if (role != UserRole.SUPER_ADMIN) {
            builder.claim("tenantId",user.getTenant().getId());
        }
        return builder.compact();
    }
}

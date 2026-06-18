package com.greencode.service;

import com.greencode.dto.AuthRequest;
import com.greencode.dto.AuthResponse;
import com.greencode.entity.User;
import com.greencode.repository.UserRepository;
import com.greencode.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Value("${app.jwtExpirationInMs:86400000}")
    private long jwtExpirationInMs;

    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsernameOrEmail(),
                    authRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            User user = userRepository.findByUsernameOrEmail(
                authRequest.getUsernameOrEmail(), 
                authRequest.getUsernameOrEmail()
            ).orElseThrow(() -> new RuntimeException("User not found"));

            String jwt = tokenProvider.generateToken(authentication);
            
            return new AuthResponse(
                jwt,
                "Authentication successful",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password", e);
        }
    }

    public AuthResponse refreshToken(String token) {
        try {
            if (tokenProvider.validateToken(token)) {
                Claims claims = tokenProvider.getClaimsFromToken(token);
                String username = claims.getSubject();
                
                User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
                
                String newToken = tokenProvider.generateToken(username);
                
                return new AuthResponse(
                    newToken,
                    "Token refreshed successfully",
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole().name()
                );
            } else {
                throw new RuntimeException("Invalid token");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh token", e);
        }
    }

    public void logout(String token) {
        try {
            if (tokenProvider.validateToken(token)) {
                SecurityContextHolder.clearContext();
            } else {
                throw new RuntimeException("Invalid token");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to logout", e);
        }
    }

    public boolean isTokenValid(String token) {
        return tokenProvider.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return tokenProvider.getUsernameFromToken(token);
    }
}

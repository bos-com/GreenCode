package com.greencode.controller;

import com.greencode.dto.AuthRequest;
import com.greencode.dto.AuthResponse;
import com.greencode.dto.RegisterRequest;
import com.greencode.entity.User;
import com.greencode.service.AuthenticationService;
import com.greencode.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        try {
            AuthResponse authResponse = authenticationService.authenticate(authRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());

            User createdUser = userService.createUser(user);
            
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsernameOrEmail(registerRequest.getUsername());
            authRequest.setPassword(registerRequest.getPassword());
            
            AuthResponse authResponse = authenticationService.authenticate(authRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(null, e.getMessage()));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            AuthResponse authResponse = authenticationService.refreshToken(token);
            return ResponseEntity.ok(authResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            authenticationService.logout(token);
            return ResponseEntity.ok("Logout successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }
}

package com.greencode.controller;

import com.greencode.dto.PasswordResetConfirmDto;
import com.greencode.dto.PasswordResetRequestDto;
import com.greencode.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/password-reset")
public class PasswordResetController {

    private final PasswordResetService service;

    public PasswordResetController(PasswordResetService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public ResponseEntity<?> request(@RequestBody PasswordResetRequestDto dto) {
        String token = service.requestReset(dto.email);

        // DEV ONLY: print token to console so you can test UI without email sending
        if (token != null) {
            System.out.println("DEV reset token for " + dto.email + ": " + token);
        }

        // Always 200 (avoid leaking user existence)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token) {
        if (service.validateToken(token)) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().body("Invalid or expired token");
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody PasswordResetConfirmDto dto) {
        service.confirmReset(dto.token, dto.newPassword);
        return ResponseEntity.ok().build();
    }
}

package com.greencode.service;

import com.greencode.entity.PasswordResetToken;
import com.greencode.entity.User;
import com.greencode.repository.PasswordResetTokenRepository;
import com.greencode.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public PasswordResetService(
            PasswordResetTokenRepository tokenRepo,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserService userService
    ) {
        this.tokenRepo = tokenRepo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String requestReset(String email) {
        // Security best practice: don’t reveal if user exists.
        // If user not found -> still return silently.
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        String token = generateToken();
        Instant expiresAt = Instant.now().plus(Duration.ofMinutes(30));
        tokenRepo.save(new PasswordResetToken(token, expiresAt, user));

        return token;
    }

    public boolean validateToken(String token) {
        return tokenRepo.findByToken(token)
                .filter(t -> !t.isUsed())
                .filter(t -> t.getExpiresAt().isAfter(Instant.now()))
                .isPresent();
    }

    public void confirmReset(String token, String newPassword) {
        PasswordResetToken prt = tokenRepo.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (prt.isUsed()) throw new IllegalArgumentException("Token already used");
        if (prt.getExpiresAt().isBefore(Instant.now())) throw new IllegalArgumentException("Token expired");

        String encoded = passwordEncoder.encode(newPassword);
        userService.updatePasswordByEmail(prt.getUser().getEmail(), encoded);

        prt.setUsed(true);
        tokenRepo.save(prt);
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}

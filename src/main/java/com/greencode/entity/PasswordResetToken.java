package com.greencode.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean used = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected PasswordResetToken() {}

    public PasswordResetToken(String token, Instant expiresAt, User user) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getToken() { return token; }
    public Instant getExpiresAt() { return expiresAt; }
    public boolean isUsed() { return used; }
    public User getUser() { return user; }

    public void setUsed(boolean used) { this.used = used; }
}

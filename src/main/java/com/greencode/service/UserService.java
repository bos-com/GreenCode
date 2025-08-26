package com.greencode.service;

import com.greencode.entity.User;
import com.greencode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if username or email already exists (excluding current user)
        if (userRepository.existsByUsernameOrEmailExcludingId(
                userDetails.getUsername(), userDetails.getEmail(), id)) {
            throw new RuntimeException("Username or email already exists");
        }

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setRole(userDetails.getRole());
        user.setIsEnabled(userDetails.getIsEnabled());

        // Only update password if provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setIsActive(false);
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

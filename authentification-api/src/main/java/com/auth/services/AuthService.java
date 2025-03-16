package com.auth.services;

import com.auth.dto.AuthResponse;
import com.auth.dto.LoginRequest;
import com.auth.models.Token;
import com.auth.models.User;
import com.auth.repositories.TokenRepository;
import com.auth.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public ResponseEntity<String> register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            String token = generateToken(request.getUsername());
            LocalDateTime expiration = LocalDateTime.now().plusHours(1);
            tokenRepository.save(new Token(token, request.getUsername(), expiration));
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body(null);
    }

    public ResponseEntity<String> validateToken(String token) {
        Optional<Token> storedToken = tokenRepository.findByToken(token);
        if (storedToken.isPresent() && storedToken.get().getExpiration().isAfter(LocalDateTime.now())) {
            storedToken.get().setExpiration(LocalDateTime.now().plusHours(1));
            tokenRepository.save(storedToken.get());
            return ResponseEntity.ok(storedToken.get().getUsername());
        }
        return ResponseEntity.status(401).body("Token expired or invalid");
    }

    public ResponseEntity<String> resetPassword(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            user.get().setPassword(request.getNewPassword());
            userRepository.save(user.get());
            return ResponseEntity.ok("Password updated successfully");
        }
        return ResponseEntity.status(400).body("Invalid credentials");
    }

    public ResponseEntity<String> logout(String token) {
        tokenRepository.deleteByToken(token);
        return ResponseEntity.ok("Logged out successfully");
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(404).body("User not found");
    }

    private String generateToken(String username) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"));
        return username + "-" + timestamp;
    }
}

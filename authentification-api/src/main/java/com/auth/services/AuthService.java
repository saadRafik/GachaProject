package com.auth.services;

import com.auth.dto.AuthResponse;
import com.auth.dto.LoginRequest;
import com.auth.models.Token;
import com.auth.models.User;
import com.auth.repositories.UserRepository;
import com.auth.utils.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate new token and save to user
        String tokenValue = TokenGenerator.generateToken(user.getUsername());
        Token token = new Token(tokenValue, LocalDateTime.now().plusHours(1));
        user.setToken(token);
        userRepository.save(user);

        return new AuthResponse(tokenValue);
    }

    public boolean isTokenValid(String tokenValue) {
        return userRepository.findByTokenValue(tokenValue)
                .filter(user -> user.getToken().getExpiration().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}

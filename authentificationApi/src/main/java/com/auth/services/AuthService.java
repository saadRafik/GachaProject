package com.auth.services;

import com.auth.dto.UserDto;
import com.auth.exceptions.ExistingUserException;
import com.auth.models.Token;
import com.auth.models.User;
import com.auth.repositories.TokenRepository;
import com.auth.repositories.UserRepository;
import com.auth.utils.TokenGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final TokenGenerator tokenGenerator;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenRepository tokenRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.tokenGenerator = tokenGenerator;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Map<String, String> register(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ExistingUserException();
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return Map.of("id", userRepository.save(newUser).getId());
    }

    public String login(UserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                // Generate token
                String token = tokenGenerator.generateToken(user.getUsername());
                Token newToken = new Token(token, user.getUsername(), LocalDateTime.now().plusHours(1));
                tokenRepository.save(newToken);
                return token;
            }
        }
        return null; // Invalid credentials
    }

    public boolean validateToken(String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token storedToken = tokenOpt.get();
            if (storedToken.getExpirationTime().isAfter(LocalDateTime.now())) {
                // Refresh token expiration time
                storedToken.setExpirationTime(LocalDateTime.now().plusHours(1));
                tokenRepository.save(storedToken);
                return true;
            }
        }
        return false; // Token is invalid or expired
    }
}

package com.auth.controllers;

import com.auth.dto.AuthResponse;
import com.auth.dto.LoginRequest;
import com.auth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    /**
     * This endpoint is called by other APIs (e.g., Player API)
     * to verify the token passed in the "Authorization" header.
     */
    @PostMapping("/verify")
    public ResponseEntity<Void> verify(@RequestHeader("Authorization") String tokenValue) {
        if (authService.isTokenValid(tokenValue)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

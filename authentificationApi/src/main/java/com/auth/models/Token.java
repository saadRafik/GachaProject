package com.auth.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "tokens")
public class Token {

    @Id
    private String token;
    private String username;
    private LocalDateTime expirationTime;

    public Token() {}

    public Token(String token, String username, LocalDateTime expirationTime) {
        this.token = token;
        this.username = username;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}

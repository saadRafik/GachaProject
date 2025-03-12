package com.auth.models;

import java.time.LocalDateTime;

public class Token {
    private String value;
    private LocalDateTime expiration;

    public Token(String value, LocalDateTime expiration) {
        this.value = value;
        this.expiration = expiration;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}

package com.auth.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    private String username;
    private String password;
    private Token token;  // Embedded token document

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

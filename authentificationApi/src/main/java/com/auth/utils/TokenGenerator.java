package com.auth.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * Generates an authentication token in the format:
 *   username-YYYY/MM/DD-HH:mm:ss
 */
@Component
public class TokenGenerator {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");

    public String generateToken(String username) {
        String timestamp = LocalDateTime.now().format(formatter);
        return username + "-" + timestamp;
    }
}

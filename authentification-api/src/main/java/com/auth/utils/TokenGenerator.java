package com.auth.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generates a custom token:
 *   username-YYYY/MM/DD-HH:mm:ss
 */
public class TokenGenerator {

    public static String generateToken(String username) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        return username + "-" + timestamp;
    }
}

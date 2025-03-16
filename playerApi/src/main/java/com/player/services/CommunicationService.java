package com.player.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommunicationService {

    private final RestTemplate restTemplate;
    private final String AUTH_API_URL = "http://auth-api:8081/auth/validate";
    private final String MONSTER_API_URL = "http://monster-api:8083/monsters/";
    private final String INVOCATION_API_URL = "http://invocation-api:8084/invoke";

    public CommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String token) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(AUTH_API_URL + "?token=" + token, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMonsterValid(String monsterId) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(MONSTER_API_URL + monsterId, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public String invokeMonster() {
        ResponseEntity<String> response = restTemplate.getForEntity(INVOCATION_API_URL, String.class);
        return response.getBody();
    }
}

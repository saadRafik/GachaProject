package com.player.services;

import com.player.models.Player;
import com.player.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final RestTemplate restTemplate;

    // This URL is set in player-api's application.properties
    @Value("${auth.api.url}")
    private String authApiUrl;

    public PlayerService(PlayerRepository playerRepository, RestTemplateBuilder builder) {
        this.playerRepository = playerRepository;
        this.restTemplate = builder.build();
    }

    public Player getPlayer(String id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public Player gainExperience(String id, int xp) {
        Player player = getPlayer(id);
        player.addExperience(xp);
        return playerRepository.save(player);
    }

    /**
     * Calls the Auth API (/verify endpoint) to ensure the token is valid.
     * If invalid, we throw an exception.
     */
    public void verifyToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            restTemplate.postForEntity(authApiUrl, request, Void.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new RuntimeException("Token invalid or expired", ex);
            }
            throw ex;
        }
    }
}

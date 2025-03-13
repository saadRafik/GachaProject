package com.player.services;

import com.player.models.Player;
import com.player.repositories.PlayerRepository;
import com.player.dto.PlayerInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Retrieve player's profile along with full monster details from monster-api
    public PlayerInfoResponse getProfile(String playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // For each monster ID, call monster-api to get its details
        List<Object> monsterDetails = new ArrayList<>();
        for (String monsterId : player.getMonsterIds()) {
            Object monster = restTemplate.getForObject("http://monster-api:8083/monsters/" + monsterId, Object.class);
            monsterDetails.add(monster);
        }

        return new PlayerInfoResponse(
                player.getId(),
                player.getUsername(),
                player.getLevel(),
                player.getExperience(),
                player.getMaxMonsters(),
                monsterDetails
        );
    }

    // Gain experience for a player; level up if necessary
    public PlayerInfoResponse gainExperience(String playerId, int xp) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        int currentXP = player.getExperience() + xp;
        // Required XP formula: 50 * 1.1^(level)
        int requiredXP = (int)(50 * Math.pow(1.1, player.getLevel()));
        if (currentXP >= requiredXP && player.getLevel() < 50) {
            player.setLevel(player.getLevel() + 1);
            player.setExperience(currentXP - requiredXP);
        } else {
            player.setExperience(currentXP);
        }
        playerRepository.save(player);
        return getProfile(playerId);
    }

    // Acquire a new monster for the player by calling monster-api and updating player's monster list
    public PlayerInfoResponse acquireMonster(String playerId, Object monsterRequest) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (player.getMonsterIds().size() >= player.getMaxMonsters()) {
            throw new RuntimeException("Maximum number of monsters reached");
        }

        // Ensure the monsterRequest contains the ownerId = playerId; you can set it here if needed
        // Call monster-api to create a monster
        @SuppressWarnings("unchecked")
        java.util.Map<String, Object> createdMonster = restTemplate.postForObject(
                "http://monster-api:8083/monsters", monsterRequest, java.util.Map.class);
        String monsterId = (String) createdMonster.get("id");
        player.getMonsterIds().add(monsterId);
        playerRepository.save(player);
        return getProfile(playerId);
    }

    // Delete a monster from player's list and remove it from monster-api
    public PlayerInfoResponse deleteMonster(String playerId, String monsterId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        if (!player.getMonsterIds().contains(monsterId)) {
            throw new RuntimeException("Monster not found in player's list");
        }
        player.getMonsterIds().remove(monsterId);
        playerRepository.save(player);
        restTemplate.delete("http://monster-api:8083/monsters/" + monsterId);
        return getProfile(playerId);
    }
}

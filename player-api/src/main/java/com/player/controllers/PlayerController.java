package com.player.controllers;

import com.player.dto.PlayerInfoResponse;
import com.player.models.Player;
import com.player.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Example endpoint: get player details by ID.
     * We'll also pass the "Authorization" header to verify the token with auth API.
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<PlayerInfoResponse> getPlayerInfo(
            @PathVariable String id,
            @RequestHeader("Authorization") String tokenValue
    ) {
        // Verify token with auth API before proceeding
        playerService.verifyToken(tokenValue);

        Player player = playerService.getPlayer(id);
        PlayerInfoResponse response = new PlayerInfoResponse(
                player.getId(),
                player.getUsername(),
                player.getLevel(),
                player.getExperience(),
                player.getMaxMonsters()
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Gain XP endpoint. In a real scenario, you'd also pass the XP in the body or path.
     */
    @PostMapping("/{id}/gain-xp/{xp}")
    public ResponseEntity<PlayerInfoResponse> gainXp(
            @PathVariable String id,
            @PathVariable int xp,
            @RequestHeader("Authorization") String tokenValue
    ) {
        playerService.verifyToken(tokenValue);

        Player updated = playerService.gainExperience(id, xp);
        PlayerInfoResponse response = new PlayerInfoResponse(
                updated.getId(),
                updated.getUsername(),
                updated.getLevel(),
                updated.getExperience(),
                updated.getMaxMonsters()
        );
        return ResponseEntity.ok(response);
    }
}

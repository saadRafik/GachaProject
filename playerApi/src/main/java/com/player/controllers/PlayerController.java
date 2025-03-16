package com.player.controllers;

import com.player.dto.PlayerDto;
import com.player.services.PlayerService;
import com.player.services.CommunicationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final CommunicationService communicationService;

    public PlayerController(PlayerService playerService, CommunicationService communicationService) {
        this.playerService = playerService;
        this.communicationService = communicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String id) {
        if (!communicationService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping("/{playerId}/acquire/{monsterId}")
    public ResponseEntity<?> acquireMonster(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                            @PathVariable String playerId,
                                            @PathVariable String monsterId) {
        if (!communicationService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
        playerService.acquireMonster(playerId, monsterId);
        return ResponseEntity.ok("Monster acquired successfully.");
    }

    @PostMapping("/{playerId}/summon")
    public ResponseEntity<?> summonMonster(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                           @PathVariable String playerId) {
        if (!communicationService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
        String monsterId = playerService.summonMonster(playerId, token);
        return ResponseEntity.ok("Summoned monster: " + monsterId);
    }

    @PostMapping("/{playerId}/gain-xp/{xp}")
    public ResponseEntity<?> gainXp(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @PathVariable String playerId,
                                    @PathVariable int xp) {
        if (!communicationService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
        playerService.gainExperience(playerId, xp);
        return ResponseEntity.ok("XP gained successfully.");
    }
}

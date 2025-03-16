package com.player.controllers;

import com.player.models.Player;
import com.player.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable @Min(1) Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/{id}/gain-exp/{amount}")
    public ResponseEntity<Player> gainExperience(@PathVariable @Min(1) Long id, @PathVariable @Min(0) int amount) {
        return playerService.gainExperience(id, amount);
    }

    @PostMapping("/{id}/level-up")
    public ResponseEntity<Player> levelUp(@PathVariable @Min(1) Long id) {
        return playerService.levelUp(id);
    }

    @PostMapping("/{id}/monsters/add/{monsterId}")
    public ResponseEntity<String> addMonster(@PathVariable @Min(1) Long id, @PathVariable @Min(1) Long monsterId) {
        return playerService.addMonster(id, monsterId);
    }

    @DeleteMapping("/{id}/monsters/remove/{monsterId}")
    public ResponseEntity<String> removeMonster(@PathVariable @Min(1) Long id, @PathVariable @Min(1) Long monsterId) {
        return playerService.removeMonster(id, monsterId);
    }
}

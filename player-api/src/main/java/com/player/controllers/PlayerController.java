package com.player.controllers;

import com.player.dto.PlayerInfoResponse;
import com.player.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Get full player profile (including monsters)
    @GetMapping("/{id}/profile")
    public PlayerInfoResponse getProfile(@PathVariable String id) {
        return playerService.getProfile(id);
    }

    // Gain experience; xp is passed as a query parameter
    @PostMapping("/{id}/gain-xp")
    public PlayerInfoResponse gainXP(@PathVariable String id, @RequestParam int xp) {
        return playerService.gainExperience(id, xp);
    }

    // Acquire a new monster; the request body should contain monster's base attributes
    @PostMapping("/{id}/acquire-monster")
    public PlayerInfoResponse acquireMonster(@PathVariable String id, @RequestBody Object monsterRequest) {
        return playerService.acquireMonster(id, monsterRequest);
    }

    // Delete a monster from player's list by monster ID
    @DeleteMapping("/{id}/monsters/{monsterId}")
    public PlayerInfoResponse deleteMonster(@PathVariable String id, @PathVariable String monsterId) {
        return playerService.deleteMonster(id, monsterId);
    }
}

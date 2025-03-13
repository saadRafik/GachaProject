package com.monster.controllers;

import com.monster.models.Monster;
import com.monster.services.MonsterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monsters")
public class MonsterController {

    private final MonsterService monsterService;

    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    // Create a new monster (for player acquisition)
    @PostMapping
    public Monster createMonster(@RequestBody Monster monster) {
        return monsterService.createMonster(monster);
    }

    // Retrieve a monster by its ID
    @GetMapping("/{id}")
    public Monster getMonster(@PathVariable String id) {
        return monsterService.getMonster(id);
    }

    // (Optional) Additional endpoints for XP gain, skill upgrade, etc.
}

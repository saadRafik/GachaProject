package com.monster.controllers;

import com.monster.models.Monster;
import com.monster.services.MonsterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monsters")
public class MonsterController {
    private final MonsterService monsterService;

    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monster> getMonster(@PathVariable Long id) {
        return monsterService.getMonsterById(id);
    }

    @PostMapping("/{id}/gain-exp/{amount}")
    public ResponseEntity<Monster> gainExperience(@PathVariable Long id, @PathVariable int amount) {
        return monsterService.gainExperience(id, amount);
    }

    @PostMapping("/{id}/upgrade-skill/{skillId}")
    public ResponseEntity<String> upgradeSkill(@PathVariable Long id, @PathVariable Long skillId) {
        return monsterService.upgradeSkill(id, skillId);
    }
}

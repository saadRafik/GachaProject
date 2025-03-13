package com.monster.services;

import com.monster.models.Monster;
import com.monster.models.Skill;
import com.monster.repositories.MonsterRepository;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {

    private final MonsterRepository monsterRepository;

    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public Monster createMonster(Monster monster) {
        // Initialize monster if needed (e.g., set level to 0, experience to 0)
        if(monster.getLevel() == 0) {
            monster.setLevel(0);
        }
        if(monster.getExperience() == 0) {
            monster.setExperience(0);
        }
        return monsterRepository.save(monster);
    }

    public Monster getMonster(String id) {
        return monsterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monster not found"));
    }

    // You can add methods to handle XP gain for monster, skill upgrade, etc.
}

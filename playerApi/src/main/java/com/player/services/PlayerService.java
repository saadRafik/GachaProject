package com.player.services;

import com.player.models.Player;
import com.player.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CommunicationService communicationService;

    public PlayerService(PlayerRepository playerRepository, CommunicationService communicationService) {
        this.playerRepository = playerRepository;
        this.communicationService = communicationService;
    }

    public Player getPlayerById(String playerId) {
//        return playerRepository.findById(playerId)
//                .orElseThrow(() -> new RuntimeException("Player not found"));

        return new Player();
    }

    public void acquireMonster(String playerId, String monsterId) {
        Player player = getPlayerById(playerId);

        if (player.getMonsterIds().size() >= player.getMaxMonsters()) {
            throw new RuntimeException("Max monster capacity reached.");
        }

        if (!communicationService.isMonsterValid(monsterId)) {
            throw new RuntimeException("Invalid monster ID.");
        }

        player.getMonsterIds().add(monsterId);
        playerRepository.save(player);
    }

    public String summonMonster(String playerId, String token) {
        Player player = getPlayerById(playerId);

        if (player.getMonsterIds().size() >= player.getMaxMonsters()) {
            throw new RuntimeException("Max monster capacity reached.");
        }

        String monsterId = communicationService.invokeMonster();
        player.getMonsterIds().add(monsterId);
        playerRepository.save(player);

        return monsterId;
    }

    public void gainExperience(String playerId, int xp) {
        Player player = getPlayerById(playerId);
        int requiredXp = (int) (50 * Math.pow(1.1, player.getLevel()));

        player.setExperience(player.getExperience() + xp);
        if (player.getExperience() >= requiredXp) {
            player.setLevel(player.getLevel() + 1);
            player.setExperience(0);
            player.updateMaxMonsters();
        }

        playerRepository.save(player);
    }
}

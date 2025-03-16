package com.player.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerDto {
    private String id;
    private String username;
    private int level;
    private int experience;
    private int maxMonsters;
    private List<String> monsterIds = new ArrayList<>();

    // Constructors
    public PlayerDto() {
    }

    public PlayerDto(String id, String username, int level, int experience, int maxMonsters, List<String> monsterIds) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.experience = experience;
        this.maxMonsters = maxMonsters;
        this.monsterIds = monsterIds;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public int getMaxMonsters() { return maxMonsters; }
    public void setMaxMonsters(int maxMonsters) { this.maxMonsters = maxMonsters; }

    public List<String> getMonsterIds() { return monsterIds; }
    public void setMonsterIds(List<String> monsterIds) { this.monsterIds = monsterIds; }
}

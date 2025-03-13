package com.player.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "players")
public class Player {
    @Id
    private String id;
    private String username;
    private int level;
    private int experience;
    private int maxMonsters;
    private List<String> monsterIds;

    public Player() {
        this.level = 0;
        this.experience = 50;
        this.maxMonsters = 10;
        this.monsterIds = new ArrayList<>();
    }

    // Getters and setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; updateMaxMonsters(); }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public int getMaxMonsters() { return maxMonsters; }
    public void setMaxMonsters(int maxMonsters) { this.maxMonsters = maxMonsters; }
    public List<String> getMonsterIds() { return monsterIds; }
    public void setMonsterIds(List<String> monsterIds) { this.monsterIds = monsterIds; }

    public void updateMaxMonsters() {
        this.maxMonsters = 10 + this.level;
    }
}

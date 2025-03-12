package com.player.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    @Id
    private String id;
    private String username;
    private int level;
    private int experience;
    private int maxMonsters;

    public Player() {
        // Default constructor for Spring Data
    }

    public Player(String id, String username, int level, int experience, int maxMonsters) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.experience = experience;
        this.maxMonsters = maxMonsters;
    }

    // Basic getters and setters

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getMaxMonsters() {
        return maxMonsters;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxMonsters(int maxMonsters) {
        this.maxMonsters = maxMonsters;
    }

    public void addExperience(int xp) {
        this.experience += xp;
        if (this.experience >= getNextLevelXP()) {
            levelUp();
        }
    }

    private void levelUp() {
        this.experience = 0;
        this.level++;
        this.maxMonsters++;
    }

    private int getNextLevelXP() {
        // As specified in the instructions: 50 * 1.1^level
        return (int) (50 * Math.pow(1.1, this.level));
    }
}

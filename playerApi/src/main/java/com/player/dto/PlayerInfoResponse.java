package com.player.dto;

import java.util.List;

public class PlayerInfoResponse {
    private String id;
    private String username;
    private int level;
    private int experience;
    private int maxMonsters;
    private List<Object> monsters; // Detailed monster info from monster-api

    public PlayerInfoResponse(String id, String username, int level, int experience, int maxMonsters, List<Object> monsters) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.experience = experience;
        this.maxMonsters = maxMonsters;
        this.monsters = monsters;
    }

    // Getters...
    public String getId() { return id; }
    public String getUsername() { return username; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public int getMaxMonsters() { return maxMonsters; }
    public List<Object> getMonsters() { return monsters; }
}

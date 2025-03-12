package com.player.dto;

public class PlayerInfoResponse {
    private String id;
    private String username;
    private int level;
    private int experience;
    private int maxMonsters;

    public PlayerInfoResponse(String id, String username, int level, int experience, int maxMonsters) {
        this.id = id;
        this.username = username;
        this.level = level;
        this.experience = experience;
        this.maxMonsters = maxMonsters;
    }

    // Getters only, to keep it immutable
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
}

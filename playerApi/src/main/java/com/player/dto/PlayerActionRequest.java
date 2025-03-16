package com.player.dto;

// DTO for the action request (customize fields as needed)
public class PlayerActionRequest {
    private String playerId;
    private String action;

    // getters and setters
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

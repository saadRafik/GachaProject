package com.monster.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "monsters")
public class Monster {
    @Id
    private String id;
    private String ownerId; // Player's ID who owns this monster
    private String elementType; // "Feu", "Eau", "Vent"
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private int level;
    private int experience;
    private List<Skill> skills;

    public Monster() {}

    // Getters and setters (generate via IDE)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public String getElementType() { return elementType; }
    public void setElementType(String elementType) { this.elementType = elementType; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getAtk() { return atk; }
    public void setAtk(int atk) { this.atk = atk; }
    public int getDef() { return def; }
    public void setDef(int def) { this.def = def; }
    public int getVit() { return vit; }
    public void setVit(int vit) { this.vit = vit; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }
}

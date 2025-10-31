package com.example.factory.builders;

import java.util.ArrayList;
import java.util.List;

import com.example.characters.Bandit;
import com.example.characters.Character;
import com.example.characters.Mage;
import com.example.characters.Warrior;
import com.example.Stats;
import com.example.equipment.Equipment;
import com.example.interfaces.Weapon;

public class CharacterBuilder {
    private String characterType;
    private Stats customStats;
    private List<Object> equipmentList;
    private String name;

    public CharacterBuilder() {
        this.equipmentList = new ArrayList<>();
    }

    public CharacterBuilder setCharacterType(String type) {
        this.characterType = type;
        return this;
    }

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setCustomStats(Stats stats) {
        this.customStats = stats;
        return this;
    }

    public CharacterBuilder addEquipment(Equipment equipment) {
        this.equipmentList.add(equipment);
        return this;
    }

    public CharacterBuilder addWeapon(Weapon weapon) {
        this.equipmentList.add(weapon);
        return this;
    }

    public CharacterBuilder addStat(String statName, int value) {
        if (this.customStats == null) {
            // Create default stats if none provided
            this.customStats = new Stats(100, 10, 10, 10, 10);
        }
        // Note: This assumes the Stats class has a method to modify existing stats
        // For now, we'll create a new Stats with the added stat
        Stats newStats = new Stats(this.customStats);
        // This is a simplified implementation - in a real scenario you'd modify the stat
        return this;
    }

    public Character build() {
        Character character = null;

        // Create the appropriate character type
        switch (characterType.toLowerCase()) {
            case "warrior":
                if (customStats != null) {
                    character = new Warrior(customStats, name);
                } else {
                    character = new Warrior();
                    if (name != null) {
                        character.setName(name);
                    }
                }
                break;
            case "mage":
                if (customStats != null) {
                    character = new Mage(customStats, name);
                } else {
                    character = new Mage();
                    if (name != null) {
                        character.setName(name);
                    }
                }
                break;
            case "bandit":
                if (customStats != null) {
                    character = new Bandit(customStats, name);
                } else {
                    character = new Bandit();
                    if (name != null) {
                        character.setName(name);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown character type: " + characterType);
        }

        // Equip all equipment
        for (Object equipment : equipmentList) {
            // Note: This is a simplified implementation
            // In a real scenario, you'd need to determine the equipment type and slot
            // For demonstration, we'll assume equipment can be equipped as weapons
            if (equipment instanceof com.example.interfaces.Weapon) {
                character.equipWeapon((com.example.interfaces.Weapon) equipment);
            }
        }

        return character;
    }
}

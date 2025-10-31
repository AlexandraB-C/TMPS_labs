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
import com.example.interfaces.Armor;

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

    public CharacterBuilder addArmor(Armor armor) {
        this.equipmentList.add(armor);
        return this;
    }

    public CharacterBuilder addStat(String statName, int value) {
        if (this.customStats == null) {
            this.customStats = new Stats(100, 10, 10, 10, 10);
        }
        Stats newStats = new Stats(this.customStats);
        return this;
    }

    public Character build() {
        Character character = null;

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

        for (Object equipment : equipmentList) {
            if (equipment instanceof com.example.interfaces.Weapon) {
                character.equipWeapon((com.example.interfaces.Weapon) equipment);
            } else if (equipment instanceof com.example.interfaces.Armor) {
                // equip armor as helmet
                character.equipHelmet((com.example.interfaces.Armor) equipment);
            }
        }

        return character;
    }
}

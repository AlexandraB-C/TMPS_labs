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
import com.example.patterns.behavioral.strategy.MeleeAttackStrategy;
import com.example.patterns.behavioral.strategy.MagicAttackStrategy;
import com.example.patterns.behavioral.strategy.BackstabAttackStrategy;

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
        switch (statName.toLowerCase()) {
            case "health":
                this.customStats.addHealth(value);
                break;
            case "strength":
                this.customStats.addStrength(value);
                break;
            case "intelligence":
                this.customStats.addIntelligence(value);
                break;
            case "dexterity":
                this.customStats.addDexterity(value);
                break;
            case "faith":
                this.customStats.addFaith(value);
                break;
            default:
                throw new IllegalArgumentException("Unknown stat: " + statName);
        }
        return this;
    }

    private Character createCharacter(String type, Stats custom, String name) {
        Character character;
        switch (type.toLowerCase()) {
            case "warrior":
                character = (custom != null) ? new Warrior(custom, name) : new Warrior();
                character.setAttackStrategy(new MeleeAttackStrategy());
                break;
            case "mage":
                character = (custom != null) ? new Mage(custom, name) : new Mage();
                character.setAttackStrategy(new MagicAttackStrategy());
                break;
            case "bandit":
                character = (custom != null) ? new Bandit(custom, name) : new Bandit();
                character.setAttackStrategy(new BackstabAttackStrategy());
                break;
            default:
                throw new IllegalArgumentException("Unknown character type: " + type);
        }
        if (custom == null && name != null) {
            character.setName(name);
        }
        return character;
    }

    public Character build() {
        Character character = createCharacter(characterType, customStats, name);

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

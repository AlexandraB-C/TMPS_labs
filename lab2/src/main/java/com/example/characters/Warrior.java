package com.example.characters;

import com.example.Stats;

public class Warrior extends Character {
    public Warrior() {
        super(new Stats(100, 20, 5, 10, 5));  // High strength, moderate health, low int
    }

    public Warrior(Stats customStats, String name) {
        super(customStats, name);
    }

    @Override
    public String getCharacterType() {
        return "Warrior";
    }
}

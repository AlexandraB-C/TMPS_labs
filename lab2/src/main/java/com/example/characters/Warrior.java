package com.example.characters;

import com.example.domain.stats.Stats;

public class Warrior extends Character {
    public Warrior() {
        super(new Stats(100, 20, 5, 10, 5));  // High strength, moderate health, low int
    }

    @Override
    public String getCharacterType() {
        return "Warrior";
    }
}

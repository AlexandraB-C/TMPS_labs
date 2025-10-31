package com.example.characters;

import com.example.Stats;

public class Bandit extends Character {
    public Bandit() {
        super(new Stats(90, 15, 10, 10, 15));  // Balanced, high faith
    }

    public Bandit(Stats customStats, String name) {
        super(customStats, name);
    }

    @Override
    public String getCharacterType() {
        return "Bandit";
    }
}

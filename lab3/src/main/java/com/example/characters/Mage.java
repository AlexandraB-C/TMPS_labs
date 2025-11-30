package com.example.characters;

import com.example.Stats;

public class Mage extends Character {
    public Mage() {
        super(new Stats(60, 5, 25, 10, 10));  // low hp, high int
    }

    public Mage(Stats customStats, String name) {
        super(customStats, name);
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }
}

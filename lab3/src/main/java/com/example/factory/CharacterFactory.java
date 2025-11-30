package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Warrior;
import com.example.characters.Mage;
import com.example.characters.Bandit;

public class CharacterFactory {
    public static Character createCharacter(String type) {
        switch (type.toLowerCase()) {
            case "warrior":
                return new Warrior();
            case "mage":
                return new Mage();
            case "bandit":
                return new Bandit();
            default:
                throw new IllegalArgumentException("Unknown character type: " + type +
                    ". Supported types: warrior, mage, bandit");
        }
    }
}

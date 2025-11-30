package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Warrior;
import com.example.characters.Mage;
import com.example.characters.Bandit;
import com.example.patterns.behavioral.strategy.MeleeAttackStrategy;
import com.example.patterns.behavioral.strategy.MagicAttackStrategy;
import com.example.patterns.behavioral.strategy.BackstabAttackStrategy;

public class CharacterFactory {
    public static Character createCharacter(String type) {
        Character character;
        switch (type.toLowerCase()) {
            case "warrior":
                character = new Warrior();
                character.setAttackStrategy(new MeleeAttackStrategy());
                break;
            case "mage":
                character = new Mage();
                character.setAttackStrategy(new MagicAttackStrategy());
                break;
            case "bandit":
                character = new Bandit();
                character.setAttackStrategy(new BackstabAttackStrategy());
                break;
            default:
                throw new IllegalArgumentException("Unknown character type: " + type +
                    ". Supported types: warrior, mage, bandit");
        }
        return character;
    }
}

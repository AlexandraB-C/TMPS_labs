package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Bandit;

public class BanditFactory extends AbstractCharacterFactory {
    @Override
    public Character createCharacter() {
        Character bandit = new Bandit();
        bandit.setName("Bandit");
        // Bandits come with daggers by default
        bandit.equipWeapon(EquipmentFactory.createWeapon("daggers"));
        return bandit;
    }

    @Override
    public String getFactoryType() {
        return "Bandit Factory";
    }
}

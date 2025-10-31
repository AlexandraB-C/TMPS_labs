package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Warrior;

public class WarriorFactory extends AbstractCharacterFactory {
    @Override
    public Character createCharacter() {
        Character warrior = new Warrior();
        warrior.setName("Warrior");
        // Warriors come with claymore by default
        warrior.equipWeapon(EquipmentFactory.createWeapon("claymore"));
        return warrior;
    }

    @Override
    public String getFactoryType() {
        return "Warrior Factory";
    }
}

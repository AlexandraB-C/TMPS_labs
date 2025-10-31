package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Mage;

public class MageFactory extends AbstractCharacterFactory {
    @Override
    public Character createCharacter() {
        Character mage = new Mage();
        mage.setName("Mage");
        // Mages come with staff by default
        mage.equipWeapon(EquipmentFactory.createWeapon("staff"));
        return mage;
    }

    @Override
    public String getFactoryType() {
        return "Mage Factory";
    }
}

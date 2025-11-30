package com.example.patterns.structural.facade;

import com.example.characters.Character;
import com.example.factory.CharacterFactory;
import com.example.factory.builders.CharacterBuilder;
import com.example.factory.EquipmentFactory;
import com.example.factory.CharacterPrototypeRegistry;
import com.example.Stats;
import com.example.weapons.IWeapon;
import com.example.equipment.IArmor;

public class GameFacade {
    public static Character createBasicCharacter(String type) {
        return CharacterFactory.createCharacter(type);
    }

    public static Character createFromPrototype(String key) {
        return CharacterPrototypeRegistry.getPrototype(key);
    }

    public static Character buildCustomCharacter(String type, String name, Stats stats, String weaponType, String armorType) {
        CharacterBuilder builder = new CharacterBuilder()
            .setCharacterType(type)
            .setName(name)
            .setCustomStats(stats);

        if (weaponType != null) {
            IWeapon weapon = EquipmentFactory.createWeapon(weaponType);
            builder.addWeapon(weapon);
        }

        if (armorType != null) {
            IArmor armor = EquipmentFactory.createArmor(armorType);
            builder.addArmor(armor);
        }

        return builder.build();
    }
}

package com.example;

import com.example.characters.Character;
import com.example.patterns.facade.GameFacade;
import com.example.factory.EquipmentFactory;
import com.example.patterns.decorator.FireEnchantment;
import com.example.patterns.decorator.HeavyUpgrade;
import com.example.patterns.decorator.HolyUpgrade;
import com.example.patterns.adapter.external.ExternalSword;
import com.example.patterns.adapter.ExternalSwordAdapter;
import com.example.interfaces.Weapon;

public class Main {
    public static void main(String[] args) {
        demonstrateStrategy();
    }

    private static void demonstrateStrategy() {
        System.out.println("STRATEGY PATTERN");
        System.out.println("Characters attacking with different strategies:\n");

        Character warrior = GameFacade.createBasicCharacter("warrior");
        Character mage = GameFacade.createBasicCharacter("mage");
        warrior.setName("Warrior");
        mage.setName("Mage");
        System.out.println("Warrior health before: " + warrior.getHealth());
        System.out.println("Mage health before: " + mage.getHealth());

        mage.attack(warrior);
        warrior.attack(mage);

        System.out.println("Warrior health after: " + warrior.getHealth());
        System.out.println("Mage health after: " + mage.getHealth());
        System.out.println();
    }
}

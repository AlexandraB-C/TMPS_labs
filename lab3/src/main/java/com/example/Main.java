package com.example;

import com.example.characters.Character;
import com.example.patterns.structural.adapter.ExternalSwordAdapter;
import com.example.patterns.structural.adapter.external.ExternalSword;
import com.example.patterns.structural.decorator.FireEnchantment;
import com.example.patterns.structural.decorator.HeavyUpgrade;
import com.example.patterns.structural.decorator.HolyUpgrade;
import com.example.patterns.structural.facade.GameFacade;
import com.example.factory.EquipmentFactory;
import com.example.interfaces.Weapon;
import com.example.patterns.behavioral.state.PoisonedState;

public class Main {
    public static void main(String[] args) {
        demonstrateStrategy();
        demonstrateState();
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

    private static void demonstrateState() {
        System.out.println("STATE PATTERN");
        System.out.println("Character states affecting stats:\n");

        Character warrior = GameFacade.createBasicCharacter("warrior");
        warrior.setName("Warrior");

        System.out.println("Stats in NormalState: " + warrior.getEffectiveStats());
        warrior.setState(new PoisonedState());
        System.out.println("Stats in PoisonedState: " + warrior.getEffectiveStats());
        System.out.println();
    }
}

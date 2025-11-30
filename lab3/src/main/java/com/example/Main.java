package com.example;

import com.example.characters.Character;
import com.example.patterns.structural.facade.GameFacade;
import com.example.factory.EquipmentFactory;
import com.example.weapons.IWeapon;
import com.example.patterns.behavioral.state.PoisonedState;
import com.example.patterns.behavioral.observer.ConsoleLoggerObserver;
import com.example.patterns.behavioral.observer.LowHealthWarningObserver;

public class Main {
    public static void main(String[] args) {
        demonstrateStrategy();
        demonstrateState();
        demonstrateObserver();
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

    private static void demonstrateObserver() {
        System.out.println("OBSERVER PATTERN");
        System.out.println("Attaching observers to track character changes:\n");

        Character warrior = GameFacade.createBasicCharacter("warrior");
        warrior.setName("Warrior");
        warrior.takeDamage(80); // Reduce health to 20 for warning
        System.out.println("Initial health: " + warrior.getHealth());

        warrior.addObserver(new ConsoleLoggerObserver());
        warrior.addObserver(new LowHealthWarningObserver());

        warrior.setState(new PoisonedState()); // Triggers notify due to state change

        // Trigger notify due to stats calculation
        warrior.getEffectiveStats();

        System.out.println();
    }
}

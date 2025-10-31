package com.example;

import com.example.characters.Character;
import com.example.factory.builders.CharacterBuilder;
import com.example.factory.CharacterFactory;
import com.example.factory.EquipmentFactory;
import com.example.factory.CharacterPrototypeRegistry;
import com.example.Stats;

public class Main {
    public static void main(String[] args) {
        demonstrateBuilder();
        demonstrateFactory();
        demonstratePrototype();

    }

    private static void demonstrateBuilder() {
        System.out.println("1. BUILDER PATTERN");
        System.out.println("Building custom characters with specific configurations:\n");

        Character mage = new CharacterBuilder()
            .setCharacterType("Mage")
            .setName("Sellen")
            .setCustomStats(new Stats(80, 8, 35, 12, 15))
            .addWeapon(EquipmentFactory.createWeapon("staff"))
            .build();

        Character warrior = new CharacterBuilder()
            .setCharacterType("Warrior")
            .setName("Bernahl")
            .setCustomStats(new Stats(120, 30, 8, 12, 8))
            .addWeapon(EquipmentFactory.createWeapon("claymore"))
            .addArmor(EquipmentFactory.createArmor("helm"))
            .build();

        System.out.println(mage.getName() + " (" + mage.getCharacterType() + ")");
        System.out.println("  " + mage.getEffectiveStats());
        System.out.println(warrior.getName() + " (" + warrior.getCharacterType() + ")");
        System.out.println("  " + warrior.getEffectiveStats());
        System.out.println();
    }

    private static void demonstrateFactory() {
        System.out.println("2. FACTORY METHOD PATTERN");
        System.out.println("Creating characters and equipment using factories:\n");

        System.out.println("CharacterFactory:");
        Character warrior = CharacterFactory.createCharacter("warrior");
        Character mage = CharacterFactory.createCharacter("mage");
        Character bandit = CharacterFactory.createCharacter("bandit");

        System.out.println("  Warrior: " + warrior.getEffectiveStats());
        System.out.println("  Mage: " + mage.getEffectiveStats());
        System.out.println("  Bandit: " + bandit.getEffectiveStats());

        System.out.println("\nEquipmentFactory:");
        System.out.println("  Staff: " + EquipmentFactory.createWeapon("staff").getDamage() +
            " " + EquipmentFactory.createWeapon("staff").getDamageType() + " damage");
        System.out.println("  Claymore: " + EquipmentFactory.createWeapon("claymore").getDamage() +
            " " + EquipmentFactory.createWeapon("claymore").getDamageType() + " damage");
        System.out.println("  Daggers: " + EquipmentFactory.createWeapon("daggers").getDamage() +
            " " + EquipmentFactory.createWeapon("daggers").getDamageType() + " damage");
        System.out.println();
    }

    private static void demonstratePrototype() {
        System.out.println("3. PROTOTYPE PATTERN");
        System.out.println("Cloning pre-configured character templates:\n");

        Character vyke = CharacterPrototypeRegistry.getPrototype("vyke");
        Character azur = CharacterPrototypeRegistry.getPrototype("azur");
        Character vargram = CharacterPrototypeRegistry.getPrototype("vargram");

        System.out.println("Templates:");
        System.out.println("  " + vyke.getName() + ": " + vyke.getEffectiveStats());
        System.out.println("  " + azur.getName() + ": " + azur.getEffectiveStats());
        System.out.println("  " + vargram.getName() + ": " + vargram.getEffectiveStats());

        System.out.println("\nCloning:");
        Character clone1 = vyke.clone();
        Character clone2 = azur.clone();

        System.out.println("  Original: " + vyke.getName());
        System.out.println("  Clone: " + clone1.getName());
        System.out.println("  Original: " + azur.getName());
        System.out.println("  Clone: " + clone2.getName());
        System.out.println();
    }
}

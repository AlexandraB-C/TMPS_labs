package com.example;

import com.example.characters.Character;
import com.example.characters.Mage;
import com.example.equipment.Helm;
import com.example.weapons.Daggers;
import com.example.weapons.Claymore;
import com.example.Stats;
import com.example.factory.builders.CharacterBuilder;
import com.example.factory.CharacterFactory;
import com.example.factory.EquipmentFactory;
import com.example.factory.AbstractCharacterFactory;
import com.example.factory.WarriorFactory;
import com.example.factory.MageFactory;
import com.example.factory.BanditFactory;
import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BUILDER PATTERN DEMONSTRATION ===\n");

        // Build a custom Mage with name "Gandalf", extra intelligence, pre-equipped staff (using Daggers as placeholder)
        CharacterBuilder mageBuilder = new CharacterBuilder();
        Character customMage = mageBuilder
            .setCharacterType("Mage")
            .setName("Gandalf")
            .setCustomStats(new Stats(80, 8, 35, 12, 15)) // Enhanced intelligence
            .addWeapon(new Daggers()) // Using Daggers as staff placeholder
            .build();

        System.out.println("Custom Mage built with Builder:");
        System.out.println("Name: " + customMage.getName());
        System.out.println("Type: " + customMage.getCharacterType());
        System.out.println("Stats: " + customMage.getEffectiveStats());

        // Build a custom Warrior with name "Conan", extra strength, pre-equipped claymore
        CharacterBuilder warriorBuilder = new CharacterBuilder();
        Character customWarrior = warriorBuilder
            .setCharacterType("Warrior")
            .setName("Conan")
            .setCustomStats(new Stats(120, 30, 8, 12, 8)) // Enhanced strength
            .addWeapon(new Claymore())
            .build();

        System.out.println("\nCustom Warrior built with Builder:");
        System.out.println("Name: " + customWarrior.getName());
        System.out.println("Type: " + customWarrior.getCharacterType());
        System.out.println("Stats: " + customWarrior.getEffectiveStats());

        // Compare with regular new Mage() creation
        Character regularMage = new Mage();
        System.out.println("\nRegular Mage (no builder):");
        System.out.println("Name: " + regularMage.getName());
        System.out.println("Type: " + regularMage.getCharacterType());
        System.out.println("Stats: " + regularMage.getEffectiveStats());

        System.out.println("\n=== Comparison ===");
        System.out.println("Builder allows:");
        System.out.println("- Custom naming");
        System.out.println("- Custom stats");
        System.out.println("- Pre-equipping items");
        System.out.println("- Fluent interface (method chaining)");
        System.out.println("- Complex object construction step-by-step");

        // Original demonstration code
        System.out.println("\n=== ORIGINAL EQUIPMENT DEMONSTRATION ===");

        Character mage = new Mage();
        System.out.println("Initial stats: " + mage.getEffectiveStats());

        Daggers daggers = new Daggers();
        mage.equipWeapon(daggers);
        System.out.println("\n+ Equipped Daggers");
        System.out.println("  Damage: " + daggers.getDamage() + " " + daggers.getDamageType());
        System.out.println(mage.getEffectiveStats());

        Claymore claymore = new Claymore();
        mage.equipWeapon(claymore); // This will replace the daggers
        System.out.println("\n+ Equipped Claymore (replaces Daggers)");
        System.out.println("  Damage: " + claymore.getDamage() + " " + claymore.getDamageType());
        System.out.println(mage.getEffectiveStats());

        Helm helm = new Helm();
        mage.equipHelmet(helm);
        System.out.println("\n+ Equipped Helm");
        System.out.println(mage.getEffectiveStats());

        // Factory Method Pattern Demonstration
        System.out.println("\n=== FACTORY METHOD PATTERN DEMONSTRATION ===\n");

        // Demonstrate CharacterFactory
        System.out.println("Creating characters using CharacterFactory:");
        Character factoryWarrior = CharacterFactory.createCharacter("warrior");
        Character factoryMage = CharacterFactory.createCharacter("mage");
        Character factoryBandit = CharacterFactory.createCharacter("bandit");

        System.out.println("Warrior: " + factoryWarrior.getCharacterType() + " - " + factoryWarrior.getEffectiveStats());
        System.out.println("Mage: " + factoryMage.getCharacterType() + " - " + factoryMage.getEffectiveStats());
        System.out.println("Bandit: " + factoryBandit.getCharacterType() + " - " + factoryBandit.getEffectiveStats());

        // Demonstrate EquipmentFactory
        System.out.println("\nCreating equipment using EquipmentFactory:");
        Weapon staff = EquipmentFactory.createWeapon("staff");
        Weapon factoryClaymore = EquipmentFactory.createWeapon("claymore");
        Armor chestplate = EquipmentFactory.createArmor("chestplate");

        System.out.println("Staff: " + staff.getDamage() + " " + staff.getDamageType() + " damage");
        System.out.println("Claymore: " + factoryClaymore.getDamage() + " " + factoryClaymore.getDamageType() + " damage");
        System.out.println("Chestplate: Defense armor");

        // Demonstrate Abstract Factory
        System.out.println("\nCreating characters using Abstract Factories (with default equipment):");
        AbstractCharacterFactory[] factories = {
            new WarriorFactory(),
            new MageFactory(),
            new BanditFactory()
        };

        for (AbstractCharacterFactory factory : factories) {
            Character character = factory.createCharacter();
            System.out.println(factory.getFactoryType() + " created: " +
                character.getName() + " (" + character.getCharacterType() + ") - " +
                character.getEffectiveStats());
        }

        // Compare with direct instantiation
        System.out.println("\n=== Comparison: Factory vs Direct Instantiation ===");
        Character directMage = new Mage();
        Character factoryMage2 = CharacterFactory.createCharacter("mage");

        System.out.println("Direct instantiation: new Mage() -> " + directMage.getEffectiveStats());
        System.out.println("Factory method: CharacterFactory.createCharacter(\"mage\") -> " + factoryMage2.getEffectiveStats());

        System.out.println("\nFactory Method benefits:");
        System.out.println("- Centralized object creation");
        System.out.println("- Easy to add new character/weapon types");
        System.out.println("- Encapsulates creation logic");
        System.out.println("- Supports different creation strategies (abstract factories)");
        System.out.println("- Reduces coupling between client code and concrete classes");
    }
}

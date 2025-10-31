package com.example;

import com.example.characters.Character;
import com.example.characters.Mage;
import com.example.characters.Warrior;
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
import com.example.factory.CharacterPrototypeRegistry;
import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CREATIONAL PATTERNS DEMO ===\n");

        // 1. BUILDER PATTERN
        System.out.println("--- BUILDER PATTERN ---\n");

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

        System.out.println("\nBuilder Pattern benefits:");
        System.out.println("- Custom naming and stats");
        System.out.println("- Pre-equipping items");
        System.out.println("- Fluent interface (method chaining)");
        System.out.println("- Complex object construction step-by-step");

        // 2. FACTORY METHOD PATTERN
        System.out.println("\n--- FACTORY METHOD PATTERN ---\n");

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
        System.out.println("\nFactory Method benefits:");
        System.out.println("- Centralized object creation");
        System.out.println("- Easy to add new character/weapon types");
        System.out.println("- Encapsulates creation logic");
        System.out.println("- Supports different creation strategies (abstract factories)");
        System.out.println("- Reduces coupling between client code and concrete classes");

        // 3. PROTOTYPE PATTERN
        System.out.println("\n--- PROTOTYPE PATTERN ---\n");

        // List available prototypes
        CharacterPrototypeRegistry.listPrototypes();

        System.out.println("\nCloning elite character templates:");
        Character eliteWarrior = CharacterPrototypeRegistry.getPrototype("elite-warrior");
        Character archmage = CharacterPrototypeRegistry.getPrototype("archmage");
        Character masterBandit = CharacterPrototypeRegistry.getPrototype("master-bandit");

        System.out.println("\nElite Warrior: " + eliteWarrior.getName() + " (" + eliteWarrior.getCharacterType() + ") - " + eliteWarrior.getEffectiveStats());
        System.out.println("Archmage: " + archmage.getName() + " (" + archmage.getCharacterType() + ") - " + archmage.getEffectiveStats());
        System.out.println("Master Bandit: " + masterBandit.getName() + " (" + masterBandit.getCharacterType() + ") - " + masterBandit.getEffectiveStats());

        // Demonstrate cloning (create copies)
        System.out.println("\nCreating clones of elite characters:");
        Character warriorClone = eliteWarrior.clone();
        Character mageClone = archmage.clone();
        Character banditClone = masterBandit.clone();

        System.out.println("\nWarrior Clone: " + warriorClone.getName() + " - " + warriorClone.getEffectiveStats());
        System.out.println("Mage Clone: " + mageClone.getName() + " - " + mageClone.getEffectiveStats());
        System.out.println("Bandit Clone: " + banditClone.getName() + " - " + banditClone.getEffectiveStats());

        // Demonstrate independence (modify clone without affecting original)
        System.out.println("\nDemonstrating clone independence:");
        System.out.println("Original Elite Warrior health: " + eliteWarrior.getEffectiveStats().getHealth());
        warriorClone.equipWeapon(EquipmentFactory.createWeapon("daggers")); // Change weapon
        System.out.println("After equipping clone with different weapon:");
        System.out.println("Original: " + eliteWarrior.getEffectiveStats().getHealth() + " health");
        System.out.println("Clone: " + warriorClone.getEffectiveStats().getHealth() + " health");

        System.out.println("\nPrototype Pattern benefits:");
        System.out.println("- Efficient creation of pre-configured objects");
        System.out.println("- Deep cloning ensures object independence");
        System.out.println("- Easy to create variations from templates");
        System.out.println("- Reduces initialization code duplication");
        System.out.println("- Registry pattern for managing prototypes");

        // 4. COMPARISON
        System.out.println("\n--- COMPARISON ---\n");

        System.out.println("Creating the same character with different patterns:");
        Stats eliteStats = new Stats(150, 35, 8, 15, 12);

        // Direct instantiation
        Character direct = new Warrior(eliteStats, "Direct Warrior");
        direct.equipWeapon(EquipmentFactory.createWeapon("claymore"));
        direct.equipHelmet(EquipmentFactory.createArmor("helm"));
        direct.equipChestArmor(EquipmentFactory.createArmor("chestplate"));

        // Builder pattern
        Character builder = new CharacterBuilder()
            .setCharacterType("Warrior")
            .setName("Builder Warrior")
            .setCustomStats(eliteStats)
            .addWeapon(EquipmentFactory.createWeapon("claymore"))
            .addArmor(EquipmentFactory.createArmor("helm"))
            .addArmor(EquipmentFactory.createArmor("chestplate"))
            .build();

        // Factory method
        Character factory = new WarriorFactory().createCharacter();
        factory.setName("Factory Warrior");

        // Prototype
        Character prototype = CharacterPrototypeRegistry.getPrototype("elite-warrior");
        prototype.setName("Prototype Warrior");

        System.out.println("Direct:     " + direct.getName() + " - " + direct.getEffectiveStats());
        System.out.println("Builder:    " + builder.getName() + " - " + builder.getEffectiveStats());
        System.out.println("Factory:    " + factory.getName() + " - " + factory.getEffectiveStats());
        System.out.println("Prototype:  " + prototype.getName() + " - " + prototype.getEffectiveStats());

        System.out.println("\n=== PATTERNS SUMMARY ===");
        System.out.println("Builder: Best for complex, step-by-step object construction");
        System.out.println("Factory Method: Best for simple object creation with variations");
        System.out.println("Prototype: Best for creating pre-configured object templates");
        System.out.println("Each pattern serves different creational needs!");
    }
}

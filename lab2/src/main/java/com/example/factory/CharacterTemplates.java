package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Warrior;
import com.example.characters.Mage;
import com.example.characters.Bandit;
import com.example.Stats;
import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;

public class CharacterTemplates {

    public static Character createEliteWarrior() {
        // Elite warrior with enhanced stats and full equipment
        Stats eliteStats = new Stats(150, 35, 8, 15, 12); // High health, strength, moderate others
        Character warrior = new Warrior(eliteStats, "Elite Warrior");

        // Equip with best gear
        Weapon claymore = EquipmentFactory.createWeapon("claymore");
        Armor helm = EquipmentFactory.createArmor("helm");
        Armor chestplate = EquipmentFactory.createArmor("chestplate");

        warrior.equipWeapon(claymore);
        warrior.equipHelmet(helm);
        warrior.equipChestArmor(chestplate);

        return warrior;
    }

    public static Character createArchmage() {
        // Archmage with high intelligence and magical equipment
        Stats mageStats = new Stats(80, 8, 40, 12, 20); // Low health, high int/magic
        Character mage = new Mage(mageStats, "Archmage");

        // Equip with magical gear
        Weapon staff = EquipmentFactory.createWeapon("staff");
        Armor helm = EquipmentFactory.createArmor("helm");

        mage.equipWeapon(staff);
        mage.equipHelmet(helm);

        return mage;
    }

    public static Character createMasterBandit() {
        // Master bandit with balanced stats and agile equipment
        Stats banditStats = new Stats(110, 18, 12, 25, 20); // Balanced, high dexterity/faith
        Character bandit = new Bandit(banditStats, "Master Bandit");

        // Equip with agile gear
        Weapon daggers = EquipmentFactory.createWeapon("daggers");
        Armor helm = EquipmentFactory.createArmor("helm");

        bandit.equipWeapon(daggers);
        bandit.equipHelmet(helm);

        return bandit;
    }
}

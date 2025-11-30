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
        Stats eliteStats = new Stats(150, 35, 8, 15, 12);
        Character warrior = new Warrior(eliteStats, "Vyke");

        Weapon claymore = EquipmentFactory.createWeapon("claymore");
        Armor helm = EquipmentFactory.createArmor("helm");

        warrior.equipWeapon(claymore);
        warrior.equipHelmet(helm);

        return warrior;
    }

    public static Character createArchmage() {
        Stats mageStats = new Stats(80, 8, 40, 12, 20);
        Character mage = new Mage(mageStats, "Azur");

        Weapon staff = EquipmentFactory.createWeapon("staff");
        Armor helm = EquipmentFactory.createArmor("helm");

        mage.equipWeapon(staff);
        mage.equipHelmet(helm);

        return mage;
    }

    public static Character createMasterBandit() {
        Stats banditStats = new Stats(110, 18, 12, 25, 20);
        Character bandit = new Bandit(banditStats, "Vargram");

        Weapon daggers = EquipmentFactory.createWeapon("daggers");
        Armor helm = EquipmentFactory.createArmor("helm");

        bandit.equipWeapon(daggers);
        bandit.equipHelmet(helm);

        return bandit;
    }
}

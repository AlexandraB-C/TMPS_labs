package com.example.factory;

import com.example.characters.Character;
import com.example.characters.Warrior;
import com.example.characters.Mage;
import com.example.characters.Bandit;
import com.example.Stats;
import com.example.weapons.IWeapon;
import com.example.equipment.IArmor;
import com.example.patterns.behavioral.strategy.MeleeAttackStrategy;
import com.example.patterns.behavioral.strategy.MagicAttackStrategy;
import com.example.patterns.behavioral.strategy.BackstabAttackStrategy;

public class CharacterTemplates {

    public static Character createEliteWarrior() {
        Stats eliteStats = new Stats(150, 35, 8, 15, 12);
        Character warrior = new Warrior(eliteStats, "Vyke");

        IWeapon claymore = EquipmentFactory.createWeapon("claymore");
        IArmor helm = EquipmentFactory.createArmor("helm");

        warrior.equipWeapon(claymore);
        warrior.equipHelmet(helm);

        warrior.setAttackStrategy(new MeleeAttackStrategy());

        return warrior;
    }

    public static Character createArchmage() {
        Stats mageStats = new Stats(80, 8, 40, 12, 20);
        Character mage = new Mage(mageStats, "Azur");

        IWeapon staff = EquipmentFactory.createWeapon("staff");
        IArmor helm = EquipmentFactory.createArmor("helm");

        mage.equipWeapon(staff);
        mage.equipHelmet(helm);

        mage.setAttackStrategy(new MagicAttackStrategy());

        return mage;
    }

    public static Character createMasterBandit() {
        Stats banditStats = new Stats(110, 18, 12, 25, 20);
        Character bandit = new Bandit(banditStats, "Vargram");

        IWeapon daggers = EquipmentFactory.createWeapon("daggers");
        IArmor helm = EquipmentFactory.createArmor("helm");

        bandit.equipWeapon(daggers);
        bandit.equipHelmet(helm);

        bandit.setAttackStrategy(new BackstabAttackStrategy());

        return bandit;
    }
}

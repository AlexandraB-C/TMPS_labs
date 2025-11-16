package com.example;

import com.example.characters.Character;
import com.example.factory.CharacterFactory;
import com.example.factory.EquipmentFactory;
import com.example.patterns.decorator.FireEnchantment;
import com.example.patterns.decorator.HeavyUpgrade;
import com.example.patterns.decorator.HolyUpgrade;
import com.example.patterns.adapter.external.ExternalSword;
import com.example.patterns.adapter.ExternalSwordAdapter;
import com.example.interfaces.Weapon;

public class Main {
    public static void main(String[] args) {
        demonstrateDecorators();
        demonstrateAdapter();
    }

    private static void demonstrateDecorators() {
        System.out.println("DECORATOR PATTERN");
        System.out.println("Decorating weapons with enchantments and upgrades:\n");

        Weapon daggers = EquipmentFactory.createWeapon("daggers");
        Weapon fireDaggers = new FireEnchantment(daggers);
        Weapon heavyFireDaggers = new HeavyUpgrade(fireDaggers);
        Weapon holyHeavyFireDaggers = new HolyUpgrade(heavyFireDaggers);

        System.out.println("Base daggers: " + daggers.getDamage() + " " + daggers.getDamageType());
        System.out.println("Fire daggers: " + fireDaggers.getDamage() + " " + fireDaggers.getDamageType());
        System.out.println("Heavy fire daggers: " + heavyFireDaggers.getDamage() + " " + heavyFireDaggers.getDamageType());
        System.out.println("Holy heavy fire daggers: " + holyHeavyFireDaggers.getDamage() + " " + holyHeavyFireDaggers.getDamageType());

        Stats stats = new Stats(100, 10, 10, 10, 10);
        holyHeavyFireDaggers.apply(stats);
        System.out.println("\nStats after equipping holy heavy fire daggers:");
        System.out.println(stats);
        System.out.println();
    }

    private static void demonstrateAdapter() {
        System.out.println("ADAPTER PATTERN");
        System.out.println("Adapting external sword to weapon interface:\n");

        ExternalSword externalSword = new ExternalSword();
        Weapon adaptedSword = new ExternalSwordAdapter(externalSword);

        System.out.println("Adapted sword: " + adaptedSword.getDamage() + " " + adaptedSword.getDamageType());

        Character warrior = CharacterFactory.createCharacter("warrior");
        warrior.equipWeapon(adaptedSword);

        System.out.println("Warrior stats after equipping adapted sword:");
        System.out.println(warrior.getEffectiveStats());
    }
}

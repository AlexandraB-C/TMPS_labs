package com.example;

import com.example.characters.Character;
import com.example.characters.Mage;
import com.example.equipment.Equipment;
import com.example.equipment.Helm;
import com.example.weapons.Daggers;
import com.example.weapons.Claymore;

public class Main {
    public static void main(String[] args) {
        Character mage = new Mage();
        System.out.println("Initial stats: " + mage.getEffectiveStats());

        Equipment weapon1 = new Daggers();
        mage.equip(weapon1);
        System.out.println("\n+ Equipped Daggers (right hand)");
        System.out.println("  Damage: " + ((Daggers) weapon1).getDamage() + " " + ((Daggers) weapon1).getDamageType());
        System.out.println(mage.getEffectiveStats());

        Equipment weapon2 = new Claymore();
        mage.equip(weapon2);
        System.out.println("\n+ Equipped Claymore (left hand)");
        System.out.println("  Damage: " + ((Claymore) weapon2).getDamage() + " " + ((Claymore) weapon2).getDamageType());
        System.out.println(mage.getEffectiveStats());

        Equipment helm = new Helm();
        mage.equip(helm);
        System.out.println("\n+ Equipped Helm");
        System.out.println(mage.getEffectiveStats());
    }
}

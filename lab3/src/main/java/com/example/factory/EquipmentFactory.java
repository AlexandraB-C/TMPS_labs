package com.example.factory;

import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;
import com.example.weapons.Daggers;
import com.example.weapons.Claymore;
import com.example.weapons.Staff;
import com.example.equipment.Helm;

public class EquipmentFactory {
    public static Weapon createWeapon(String type) {
        switch (type.toLowerCase()) {
            case "daggers":
                return new Daggers();
            case "claymore":
                return new Claymore();
            case "staff":
                return new Staff();
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + type +
                    ". Supported types: daggers, claymore, staff");
        }
    }

    public static Armor createArmor(String type) {
        switch (type.toLowerCase()) {
            case "helm":
                return new Helm();
            default:
                throw new IllegalArgumentException("Unknown armor type: " + type +
                    ". Supported types: helm");
        }
    }
}

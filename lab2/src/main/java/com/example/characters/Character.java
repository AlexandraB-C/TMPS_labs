package com.example.characters;

import java.util.HashMap;
import java.util.Map;

import com.example.Stats;
import com.example.equipment.EquipmentSlot;
import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;
import com.example.interfaces.Accessory;

public abstract class Character {
    protected Stats baseStats;
    protected Map<EquipmentSlot, Object> equipmentSlots;
    protected String name;

    public Character(Stats baseStats) {
        this.baseStats = new Stats(baseStats);
        this.equipmentSlots = new HashMap<>();
    }

    protected Character(Stats baseStats, String name) {
        this.baseStats = new Stats(baseStats);
        this.equipmentSlots = new HashMap<>();
        this.name = name;
    }

    public void equipWeapon(Weapon weapon) {
        equipmentSlots.put(EquipmentSlot.WEAPON, weapon);
    }

    public void equipHelmet(Armor helmet) {
        equipmentSlots.put(EquipmentSlot.HELMET, helmet);
    }

    public void equipChestArmor(Armor chestArmor) {
        equipmentSlots.put(EquipmentSlot.CHEST_ARMOR, chestArmor);
    }

    public void equipLegArmor(Armor legArmor) {
        equipmentSlots.put(EquipmentSlot.LEG_ARMOR, legArmor);
    }

    public void equipAccessory1(Accessory accessory) {
        equipmentSlots.put(EquipmentSlot.ACCESSORY_1, accessory);
    }

    public void equipAccessory2(Accessory accessory) {
        equipmentSlots.put(EquipmentSlot.ACCESSORY_2, accessory);
    }

    public Stats getEffectiveStats() {
        Stats effective = new Stats(baseStats);
        for (Object equip : equipmentSlots.values()) {
            if (equip instanceof Weapon) {
                ((Weapon) equip).apply(effective);
            } else if (equip instanceof Armor) {
                ((Armor) equip).apply(effective);
            } else if (equip instanceof Accessory) {
                ((Accessory) equip).apply(effective);
            }
        }
        return effective;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getCharacterType();
}

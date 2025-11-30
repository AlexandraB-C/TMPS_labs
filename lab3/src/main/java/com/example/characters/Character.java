package com.example.characters;

import java.util.HashMap;
import java.util.Map;

import com.example.Stats;
import com.example.equipment.EquipmentSlot;
import com.example.interfaces.Weapon;
import com.example.interfaces.Armor;
import com.example.interfaces.Accessory;
import com.example.patterns.behavioral.strategy.AttackStrategy;
import com.example.patterns.behavioral.state.CharacterState;
import com.example.patterns.behavioral.state.NormalState;

public abstract class Character implements Cloneable {
    protected Stats baseStats;
    protected Map<EquipmentSlot, Object> equipmentSlots;
    protected String name;
    private AttackStrategy attackStrategy;
    private CharacterState state = new NormalState();

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
        state.applyStateEffects(effective);
        return effective;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Character clone() {
        try {
            Character cloned = (Character) super.clone();
            // deep copy stats
            cloned.baseStats = new Stats(this.baseStats);
            // shallow copy equipment
            cloned.equipmentSlots = new HashMap<>(this.equipmentSlots);
            // add copy suffix to name
            if (this.name != null) {
                cloned.name = this.name + " (Copy)";
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    public abstract String getCharacterType();

    public void takeDamage(int damage) {
        baseStats.addHealth(-damage);
    }

    public int getHealth() {
        return baseStats.getHealth();
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void attack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.execute(this, target);
        } else {
            System.out.println(getName() + " has no attack strategy set.");
        }
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public CharacterState getState() {
        return state;
    }
}

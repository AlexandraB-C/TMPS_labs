package com.example.characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Stats;
import com.example.equipment.EquipmentSlot;
import com.example.weapons.IWeapon;
import com.example.equipment.IArmor;
import com.example.equipment.IAccessory;
import com.example.patterns.behavioral.strategy.IAttackStrategy;
import com.example.patterns.behavioral.state.ICharacterState;
import com.example.patterns.behavioral.state.NormalState;
import com.example.patterns.behavioral.observer.ISubject;
import com.example.patterns.behavioral.observer.IObserver;

public abstract class Character implements ISubject, Cloneable {
    protected Stats baseStats;
    protected Map<EquipmentSlot, Object> equipmentSlots;
    protected String name;
    private IAttackStrategy attackStrategy;
    private ICharacterState state = new NormalState();
    private List<IObserver> observers = new ArrayList<>();

    public Character(Stats baseStats) {
        this.baseStats = new Stats(baseStats);
        this.equipmentSlots = new HashMap<>();
    }

    protected Character(Stats baseStats, String name) {
        this.baseStats = new Stats(baseStats);
        this.equipmentSlots = new HashMap<>();
        this.name = name;
    }

    public void equipWeapon(IWeapon weapon) {
        equipmentSlots.put(EquipmentSlot.WEAPON, weapon);
    }

    public void equipHelmet(IArmor helmet) {
        equipmentSlots.put(EquipmentSlot.HELMET, helmet);
    }

    public void equipChestArmor(IArmor chestArmor) {
        equipmentSlots.put(EquipmentSlot.CHEST_ARMOR, chestArmor);
    }

    public void equipLegArmor(IArmor legArmor) {
        equipmentSlots.put(EquipmentSlot.LEG_ARMOR, legArmor);
    }

    public void equipAccessory1(IAccessory accessory) {
        equipmentSlots.put(EquipmentSlot.ACCESSORY_1, accessory);
    }

    public void equipAccessory2(IAccessory accessory) {
        equipmentSlots.put(EquipmentSlot.ACCESSORY_2, accessory);
    }

    public Stats getEffectiveStats() {
        Stats effective = new Stats(baseStats);
        for (Object equip : equipmentSlots.values()) {
            if (equip instanceof IWeapon) {
                ((IWeapon) equip).apply(effective);
            } else if (equip instanceof IArmor) {
                ((IArmor) equip).apply(effective);
            } else if (equip instanceof IAccessory) {
                ((IAccessory) equip).apply(effective);
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

    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void attack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.execute(this, target);
        } else {
            System.out.println(getName() + " has no attack strategy set.");
        }
    }

    public void setState(ICharacterState state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(this);
        }
    }

    public ICharacterState getState() {
        return state;
    }
}

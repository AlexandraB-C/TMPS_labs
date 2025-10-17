package com.example.characters;

import java.util.ArrayList;
import java.util.List;

import com.example.Stats;
import com.example.equipment.Equipment;

public abstract class Character {
    protected Stats baseStats;
    protected List<Equipment> equipment;

    public Character(Stats baseStats) {
        this.baseStats = new Stats(baseStats);
        this.equipment = new ArrayList<>();
    }

    public void equip(Equipment item) {
        equipment.add(item);
    }

    public Stats getEffectiveStats() {
        Stats effective = new Stats(baseStats);
        for (Equipment equip : equipment) {
            equip.apply(effective);
        }
        return effective;
    }

    public abstract String getCharacterType();
}

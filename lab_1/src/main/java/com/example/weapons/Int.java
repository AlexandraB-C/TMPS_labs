package com.example.weapons;

import com.example.Stats;
import com.example.equipment.Equipment;

public abstract class Int implements Equipment {
    @Override
    public void apply(Stats stats) {
        stats.addIntelligence(10);
    }
}

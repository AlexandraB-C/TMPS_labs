package com.example.weapons;

import com.example.Stats;
import com.example.equipment.Equipment;

public abstract class Dex implements Equipment {
    @Override
    public void apply(Stats stats) {
        stats.addStrength(5);
    }
}

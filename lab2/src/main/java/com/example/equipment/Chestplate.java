package com.example.equipment;

import com.example.Stats;
import com.example.interfaces.Armor;

public class Chestplate implements Armor {
    @Override
    public void apply(Stats stats) {
        stats.addHealth(30);
        stats.addStrength(5);
    }
}

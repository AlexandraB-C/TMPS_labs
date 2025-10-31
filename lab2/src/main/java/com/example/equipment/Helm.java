package com.example.equipment;

import com.example.domain.stats.Stats;
import com.example.interfaces.Armor;

public class Helm implements Equipment, Armor {
    @Override
    public void apply(Stats stats) {
        stats.addHealth(15);
    }
}

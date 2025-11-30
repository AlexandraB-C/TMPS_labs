package com.example.equipment;

import com.example.Stats;
import com.example.interfaces.Armor;

public class Helm implements Armor {
    @Override
    public void apply(Stats stats) {
        stats.addHealth(15);
    }
}

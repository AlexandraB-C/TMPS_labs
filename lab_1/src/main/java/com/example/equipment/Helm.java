package com.example.equipment;

import com.example.Stats;

public class Helm implements Equipment {
    @Override
    public void apply(Stats stats) {
        stats.addHealth(15);
    }
}

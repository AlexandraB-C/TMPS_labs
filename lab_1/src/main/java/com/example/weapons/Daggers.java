package com.example.weapons;

import com.example.Stats;

public class Daggers extends Dex {
    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addDexterity(10);
    }
}

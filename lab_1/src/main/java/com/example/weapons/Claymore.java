package com.example.weapons;

import com.example.Stats;

public class Claymore extends Str {
    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addHealth(20);
    }
}

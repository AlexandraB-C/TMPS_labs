package com.example.patterns.adapter.external;

import com.example.Stats;

public class ExternalSword {
    public void buffStats(Stats stats) {
        stats.addStrength(8);
        stats.addDexterity(5);
    }

    public int getAttackPower() {
        return 35;
    }

    public String getElement() {
        return "lightning";
    }
}

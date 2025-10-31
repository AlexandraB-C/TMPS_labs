package com.example.weapons;

import com.example.domain.stats.Stats;
import com.example.interfaces.Weapon;
import com.example.domain.damage.DamageType;

public class Daggers implements Weapon {
    @Override
    public void apply(Stats stats) {
        stats.addStrength(5);
        stats.addDexterity(10);
    }

    @Override
    public int getDamage() {
        return 30;
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.PHYSICAL;
    }
}

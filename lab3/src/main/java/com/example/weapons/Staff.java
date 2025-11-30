package com.example.weapons;

import com.example.Stats;
import com.example.domain.damage.DamageType;

public class Staff implements IWeapon {
    @Override
    public void apply(Stats stats) {
        stats.addIntelligence(15);
        stats.addFaith(5);
    }

    @Override
    public int getDamage() {
        return 25;
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.MAGICAL;
    }
}

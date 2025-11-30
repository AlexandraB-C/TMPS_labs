package com.example.weapons;

import com.example.Stats;
import com.example.interfaces.Weapon;
import com.example.domain.damage.DamageType;

public class Claymore implements Weapon {
    @Override
    public void apply(Stats stats) {
        stats.addStrength(10);
        stats.addHealth(20);
    }

    @Override
    public int getDamage() {
        return 45;
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.PHYSICAL;
    }
}

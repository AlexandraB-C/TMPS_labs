package com.example.patterns.structural.decorator;

import com.example.Stats;
import com.example.domain.damage.DamageType;
import com.example.weapons.IWeapon;

public class HeavyUpgrade extends WeaponDecorator {
    public HeavyUpgrade(IWeapon weapon) {
        super(weapon);
    }

    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addStrength(10);
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 15;
    }
}

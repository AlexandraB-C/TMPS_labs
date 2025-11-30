package com.example.patterns.decorator;

import com.example.Stats;
import com.example.domain.damage.DamageType;
import com.example.interfaces.Weapon;

public class HolyUpgrade extends WeaponDecorator {
    public HolyUpgrade(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addFaith(8);
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 12;
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.HOLY;
    }
}

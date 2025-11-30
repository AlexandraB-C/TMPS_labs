package com.example.patterns.structural.decorator;

import com.example.Stats;
import com.example.domain.damage.DamageType;
import com.example.weapons.IWeapon;

public class FireEnchantment extends WeaponDecorator {
    public FireEnchantment(IWeapon weapon) {
        super(weapon);
    }

    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addIntelligence(5);
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 10;
    }

    @Override
    public DamageType getDamageType() {
        return DamageType.FIRE;
    }
}

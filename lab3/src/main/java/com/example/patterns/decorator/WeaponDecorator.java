package com.example.patterns.decorator;

import com.example.Stats;
import com.example.interfaces.Weapon;
import com.example.domain.damage.DamageType;

public abstract class WeaponDecorator implements Weapon {
    protected Weapon weapon;

    public WeaponDecorator(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void apply(Stats stats) {
        weapon.apply(stats);
    }

    @Override
    public int getDamage() {
        return weapon.getDamage();
    }

    @Override
    public DamageType getDamageType() {
        return weapon.getDamageType();
    }
}

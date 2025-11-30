package com.example.patterns.structural.decorator;

import com.example.Stats;
import com.example.weapons.IWeapon;
import com.example.domain.damage.DamageType;

public abstract class WeaponDecorator implements IWeapon {
    protected IWeapon weapon;

    public WeaponDecorator(IWeapon weapon) {
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

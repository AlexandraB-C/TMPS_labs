package com.example.weapons;

import com.example.domain.damage.DamageType;
import com.example.Stats;

public interface IWeapon {
    void apply(Stats stats);
    int getDamage();
    DamageType getDamageType();
}

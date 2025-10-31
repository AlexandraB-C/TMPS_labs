package com.example.interfaces;

import com.example.domain.damage.DamageType;
import com.example.Stats;

public interface Weapon {
    void apply(Stats stats);
    int getDamage();
    DamageType getDamageType();
}

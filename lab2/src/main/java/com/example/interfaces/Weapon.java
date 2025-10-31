package com.example.interfaces;

import com.example.equipment.Equipment;
import com.example.domain.damage.DamageType;

public interface Weapon extends Equipment {
    int getDamage();
    DamageType getDamageType();
}

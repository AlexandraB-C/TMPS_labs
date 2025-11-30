package com.example.patterns.structural.adapter;

import com.example.Stats;
import com.example.domain.damage.DamageType;
import com.example.weapons.IWeapon;
import com.example.patterns.structural.adapter.external.ExternalSword;

public class ExternalSwordAdapter implements IWeapon {
    private ExternalSword externalSword;

    public ExternalSwordAdapter(ExternalSword externalSword) {
        this.externalSword = externalSword;
    }

    @Override
    public void apply(Stats stats) {
        externalSword.buffStats(stats);
    }

    @Override
    public int getDamage() {
        return externalSword.getAttackPower();
    }

    @Override
    public DamageType getDamageType() {
        String element = externalSword.getElement();
        switch (element) {
            case "lightning":
                return DamageType.MAGICAL; // map to magical
            default:
                return DamageType.PHYSICAL;
        }
    }
}

package com.example.patterns.behavioral.strategy;

import com.example.characters.Character;

public class MeleeAttackStrategy implements AttackStrategy {
    @Override
    public void execute(Character attacker, Character target) {
        int damage = attacker.getEffectiveStats().getStrength();
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " deals " + damage + " melee damage to " + target.getName() + ".");
    }
}

package com.example.patterns.behavioral.strategy;

import com.example.characters.Character;

public class MagicAttackStrategy implements AttackStrategy {
    @Override
    public void execute(Character attacker, Character target) {
        int damage = attacker.getEffectiveStats().getIntelligence();
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " deals " + damage + " magic damage to " + target.getName() + ".");
    }
}

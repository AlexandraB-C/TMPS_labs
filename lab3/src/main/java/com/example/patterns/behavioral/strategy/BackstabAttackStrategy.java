package com.example.patterns.behavioral.strategy;

import com.example.characters.Character;

public class BackstabAttackStrategy implements IAttackStrategy {
    @Override
    public void execute(Character attacker, Character target) {
        int damage = attacker.getEffectiveStats().getDexterity() * 2;
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " deals " + damage + " backstab damage to " + target.getName() + ".");
    }
}

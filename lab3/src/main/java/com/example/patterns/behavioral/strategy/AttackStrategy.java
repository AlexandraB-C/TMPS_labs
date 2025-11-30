package com.example.patterns.behavioral.strategy;

import com.example.characters.Character;

public interface AttackStrategy {
    void execute(Character attacker, Character target);
}

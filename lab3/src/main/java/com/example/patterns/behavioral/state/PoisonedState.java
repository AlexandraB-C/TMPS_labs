package com.example.patterns.behavioral.state;

import com.example.Stats;

public class PoisonedState implements ICharacterState {
    @Override
    public void applyStateEffects(Stats stats) {
        stats.addHealth(-5);
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}

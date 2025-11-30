package com.example.patterns.behavioral.state;

import com.example.Stats;

public class NormalState implements CharacterState {
    @Override
    public void applyStateEffects(Stats stats) {
        // do nothing
    }

    @Override
    public String getName() {
        return "Normal";
    }
}

package com.example.patterns.behavioral.state;

import com.example.Stats;

public interface CharacterState {
    void applyStateEffects(Stats stats);
    String getName();
}

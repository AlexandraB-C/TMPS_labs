package com.example.patterns.behavioral.state;

import com.example.Stats;

public interface ICharacterState {
    void applyStateEffects(Stats stats);
    String getName();
}

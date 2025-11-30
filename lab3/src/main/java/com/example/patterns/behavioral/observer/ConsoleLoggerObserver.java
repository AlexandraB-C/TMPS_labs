package com.example.patterns.behavioral.observer;

import com.example.characters.Character;

public class ConsoleLoggerObserver implements IObserver {
    @Override
    public void update(Character character) {
        System.out.println("Observer Logger: Character " + character.getName() +
            " - Stats: " + character.getEffectiveStats() +
            " - State: " + character.getState().getName());
    }
}

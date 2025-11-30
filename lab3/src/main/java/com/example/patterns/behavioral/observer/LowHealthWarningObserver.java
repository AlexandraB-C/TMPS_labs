package com.example.patterns.behavioral.observer;

import com.example.characters.Character;

public class LowHealthWarningObserver implements IObserver {
    @Override
    public void update(Character character) {
        if (character.getHealth() < 30) {
            System.out.println("LOW HEALTH WARNING: Character " + character.getName() + " health is " + character.getHealth() + "!");
        }
    }
}

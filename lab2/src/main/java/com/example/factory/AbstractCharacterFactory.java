package com.example.factory;

import com.example.characters.Character;

public abstract class AbstractCharacterFactory {
    public abstract Character createCharacter();

    public abstract String getFactoryType();
}

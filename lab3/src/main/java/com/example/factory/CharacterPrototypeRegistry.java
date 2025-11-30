package com.example.factory;

import java.util.HashMap;
import java.util.Map;
import com.example.characters.Character;

public class CharacterPrototypeRegistry {
    private static final Map<String, Character> prototypes = new HashMap<>();

    static {
        // pre-register elite templates
        prototypes.put("vyke", CharacterTemplates.createEliteWarrior());
        prototypes.put("azur", CharacterTemplates.createArchmage());
        prototypes.put("vargram", CharacterTemplates.createMasterBandit());
    }

    public static void registerPrototype(String key, Character prototype) {
        prototypes.put(key, prototype);
    }

    public static Character getPrototype(String key) {
        Character prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype not found: " + key +
                ". Available prototypes: " + prototypes.keySet());
        }
        return prototype.clone();
    }

    public static boolean hasPrototype(String key) {
        return prototypes.containsKey(key);
    }

    public static void listPrototypes() {
        System.out.println("Available prototypes:");
        for (String key : prototypes.keySet()) {
            Character proto = prototypes.get(key);
            System.out.println("- " + key + ": " + proto.getCharacterType() +
                " - " + proto.getEffectiveStats());
        }
    }
}

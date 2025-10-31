package com.example.domain.stats;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    private final Map<String, Integer> stats = new HashMap<>();

    public Stats(int health, int strength, int intelligence, int dexterity, int faith) {
        stats.put("health", health);
        stats.put("strength", strength);
        stats.put("intelligence", intelligence);
        stats.put("dexterity", dexterity);
        stats.put("faith", faith);
    }

    public Stats(Stats other) {
        stats.putAll(other.stats);
    }

    public Stats(Map<String, Integer> stats) {
        this.stats.putAll(stats);
    }

    public int getStat(String name) {
        return stats.getOrDefault(name, 0);
    }

    public void addStat(String name, int value) {
        stats.put(name, getStat(name) + value);
    }

    public boolean hasStat(String name) {
        return stats.containsKey(name);
    }

    public int getHealth() { return getStat("health"); }
    public int getStrength() { return getStat("strength"); }
    public int getIntelligence() { return getStat("intelligence"); }
    public int getDexterity() { return getStat("dexterity"); }
    public int getFaith() { return getStat("faith"); }

    public void addHealth(int value) { addStat("health", value); }
    public void addStrength(int value) { addStat("strength", value); }
    public void addIntelligence(int value) { addStat("intelligence", value); }
    public void addDexterity(int value) { addStat("dexterity", value); }
    public void addFaith(int value) { addStat("faith", value); }

    @Override
    public String toString() {
        return "Stats{" +
                "health=" + getHealth() + ", strength=" + getStrength() + ", intelligence=" + getIntelligence() + ", dexterity=" + getDexterity() + ", faith=" + getFaith() + '}';
    }
}

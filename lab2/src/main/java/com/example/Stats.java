package com.example;

public class Stats {
    private int health;
    private int strength;
    private int intelligence;
    private int dexterity;
    private int faith;

    public Stats(int health, int strength, int intelligence, int dexterity, int faith) {
        this.health = health;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.faith = faith;
    }

    public Stats(Stats stats) {
        this.health = stats.health;
        this.strength = stats.strength;
        this.intelligence = stats.intelligence;
        this.dexterity = stats.dexterity;
        this.faith = stats.faith;
    }

    public int getHealth() { return health; }
    public int getStrength() { return strength; }
    public int getIntelligence() { return intelligence; }
    public int getDexterity() { return dexterity; }
    public int getFaith() { return faith; }

    public void addHealth(int value) { health += value; }
    public void addStrength(int value) { strength += value; }
    public void addIntelligence(int value) { intelligence += value; }
    public void addDexterity(int value) { dexterity += value; }
    public void addFaith(int value) { faith += value; }

    @Override
    public String toString() {
        return "Stats{" +
                "health=" + health + ", strength=" + strength + ", intelligence=" + intelligence + ", dexterity=" + dexterity + ", faith=" + faith + '}';
    }
}

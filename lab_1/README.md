# SOLID Principles Lab Report: RPG Character Equipment System

## Theory

SOLID is an acronym for the first five object-oriented design (OOD) principles by Robert C. Martin (also known as Uncle Bob).

These principles establish practices for developing software with considerations for maintaining and extending it as the project grows. Adopting these practices can also help avoid code smells, refactor code, and develop Agile or Adaptive software.

SOLID stands for:

S - Single-responsibility Principle

O - Open-closed Principle

L - Liskov Substitution Principle

I - Interface Segregation Principle

D - Dependency Inversion Principle

## Objective

Implement 3 SOLID letters in a simple project.

## Implementation

### 1. Single Responsibility

Each class has a clear, single purpose. For example, the `Mage` class only defines what a Mage is, like low health, high intelligence.

```java
public class Mage extends Character {
    public Mage() {
        super(new Stats(60, 5, 25, 10, 10));
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }
}
```

The `Mage` doesn't manage equipment, calculate stats, or handle damage. It just is. Similarly, `Helm` only adds health:

```java
public class Helm implements Equipment {
    @Override
    public void apply(Stats stats) {
        stats.addHealth(15);
    }
}
```

### 2. Open/Closed Principle

We designed the system so you can add new things without touching existing code.

For weapons, we have a two tier system. First tier are weapon types like `Str`, `Dex`, and `Int`:

```java
public abstract class Str implements Equipment {
    @Override
    public void apply(Stats stats) {
        stats.addStrength(10);
    }
}
```

Then, specific weapons extend these types:

```java
public class Claymore extends Str {
    @Override
    public void apply(Stats stats) {
        super.apply(stats);
        stats.addHealth(20);
    }
}
```

Want to add a new weapon like "Hand_of_Malenia"? Just extend `Dex` and add its bonuses.

### 3. Dependency Injection

Characters don't create their own equipment. Instead, we pass equipment to them:

```java
Equipment weapon = new Daggers();
mage.equip(weapon);  // inject the weapon into the character
```

The `Character` class receives equipment through the `equip()` method and stores it in a list:

```java
public void equip(Equipment item) {
    equipment.add(item);  // receives equipment from outside
}
```

This way, characters don't depend on specific equipment types. They depend on the `Equipment` interface, which is the abstraction. You could pass any equipment, any weapon, and it would work.

### 4. Output

When run:

```java
Character mage = new Mage();
System.out.println("Initial stats: " + mage.getEffectiveStats());

Equipment weapon1 = new Daggers();
mage.equip(weapon1);
System.out.println("With Daggers: " + mage.getEffectiveStats());

Equipment weapon2 = new Claymore();
mage.equip(weapon2);
System.out.println("With Claymore: " + mage.getEffectiveStats());
```

The `getEffectiveStats()` method loops through all equipped items and applies their stat bonuses:

```java
public Stats getEffectiveStats() {
    Stats effective = new Stats(baseStats);
    for (Equipment equip : equipment) {
        equip.apply(effective);
    }
    return effective;
}
```

Every equipment piece is treated the same way through the `Equipment` interface.

## Conclusion

By using SOLID principles, we built a system that is flexible, only because each piece has a single, clear responsibility. We don't have to modify existing classes every time we add something new. And everything is loosely coupled through interfaces, so changing one thing doesn't break others.

Howether, this project doesn't fully implement Liskov Substitution and Interface Segregation, for the reason that, we can equip unlimited items (weapons, armor, everything goes in the same list). This does not work for "Helm", it is not really substitutable with a "Weapon", obviously. They should not follow the same rules. Also, the `Equipment` interface is general, which is fine, but we could split it into more specific interfaces like `Weapon`, `Armor`, and `Accessory` if we needed stricter type checking.

In case it is required to fix the system for the other principles, we would add equipment slots (rightHand, leftHand, helm, armor) so each slot enforces what can go there. (A Helm can only go in the helmet slot) Thurthermore, we ould create separate interfaces instead of one generic `Equipment` interface. This way, weapons follow weapon rules and armor follows armor rules.
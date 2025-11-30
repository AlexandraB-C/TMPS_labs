# Behavioral Design Patterns Lab Report

## Theory

Behavioral design patterns are ways to handle how objects talk to each other and share responsibilities. They help make objects work together better without hardcoding everything inside them. These patterns are about communication and delegation.

The main idea is to make code more flexible so when things change, you don't have to rewrite everything.

Here are the key behavioral patterns:

**Chain of Responsibility** - Passes requests along a chain of handlers. Like a help desk escalating issues to the right person.

**Command** - Turns a request into an object. Like queuing game actions or undo/redo for user inputs.

**Interpreter** - Defines a grammar and parses inputs. Good for scripting languages or config files.

**Iterator** - Provides a way to access elements without exposing internals. Like stepping through a collection.

**Mediator** - Reduces coupling by having objects communicate through a central hub. Like a chat room coordinator.

**Memento** - Captures and restores object states without breaking encapsulation. Like save/load game progress.

**Observer** - Like a newsletter subscription. When something important happens to a character, all interested parties get notified automatically, without the character knowing who they are.

**State** - Like how moods change behavior. A hurt character might act differently (take extra damage) compared to a healthy one. The object changes behavior based on its current condition.

**Strategy** - Like choosing different tools for different jobs. A character can have different ways to attack, and you can swap them on the fly without changing the character itself.

**Template Method** - Defines a skeleton algorithm, lets subclasses fill in details. Like a game level that has a fixed structure but customizable content.

**Visitor** - Adds new operations to existing classes without changing them. Like adding new effects to different item types.


## Objectives

1. Study and understand Behavioral Design Patterns
2. Implement Strategy, State, and Observer
3. Demonstrate the patterns working in code

## Project Structure

![alt text](img/image.png)

We implemented 3 behavioral patterns: Strategy, State, and Observer. Each has its own folder under `patterns/behavioral/`:

- `strategy/` - Contains attack strategies for characters
- `state/` - Contains state classes that modify character behavior
- `observer/` - Contains observer classes that react to character changes

## Implementation

### 1. Strategy

The Strategy pattern lets us swap out character attack behaviors at runtime. Warriors start with melee attacks (strength-based damage), but can switch to magic if needed. The character class stays clean - it just has a strategy field it delegates to.

We created attack strategies in:

![alt text](img/image%20copy.png)

- MeleeAttackStrategy (damage = strength)
- MagicAttackStrategy (damage = intelligence)
- BackstabAttackStrategy (damage = dexterity * 2)

Character class got setAttackStrategy() to change behavior.

```java
public class MeleeAttackStrategy implements IAttackStrategy {
    @Override
    public void execute(Character attacker, Character target) {
        int damage = attacker.getEffectiveStats().getStrength();
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " deals " + damage + " melee damage to " + target.getName() + ".");
    }
}
```

The Character class delegates attacks:

```java
public void attack(Character target) {
    if (attackStrategy != null) {
        attackStrategy.execute(this, target);
    } else {
        System.out.println(getName() + " has no attack strategy set.");
    }
}
```

![alt text](img/image%20copy%202.png)

Output shows warriors using melee (strength-based) damaging mages, mages using magic (intelligence-based) damaging warriors. Each attack uses different calculations, proving strategy swapping works without changing Character code.

What we did: Created warriors and mages with different factory-assigned strategies, called attack() multiple times. Expected different damage calculations (warrior uses strength for melee, mage uses intelligence for magic). It worked perfectly - strategy objects handle the logic, Character just delegates, enabling easy runtime changes.

### 2. State

The State pattern changes how characters behave based on their current condition. Normal state does nothing to stats. Poisoned state reduces health by 5 every time stats are checked (like ongoing damage).

![alt text](img/image%20copy%204.png)

- NormalState (no effect)
- PoisonedState (lowers health)

Character.setState() changes behavior instantly.

NormalState (no effect) and PoisonedState (reduces health):

```java
public class PoisonedState implements ICharacterState {
    @Override
    public void applyStateEffects(Stats stats) {
        stats.addHealth(-5);
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}
```

Character integrates state by calling applyStateEffects in getEffectiveStats:

```java
state.applyStateEffects(effective);
return effective;
```

![alt text](img/image%20copy%205.png)

Output shows health dropping from 100 to 95 when switching to PoisonedState, demonstrating state altering effective stats on-the-fly without external conditions.

What we did: Created a warrior, showed stats in NormalState (unmodified), switched to PoisonedState. Expected health reduction every stats calculation due to ongoing poison effect. It worked - the state object modifies stats dynamically, keeping Character clean and extensible.

### 3. Observer

The Observer pattern lets multiple things react when character data changes. We attach watchers that get notified on important events.

![alt text](img/image%20copy%203.png)

- ConsoleLoggerObserver (prints name, stats, state changes)
- LowHealthWarningObserver (warns if health drops below 30)

Character implements ISubject and calls notifyObservers() when state changes.

```java
public interface ISubject {
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();
}

public interface IObserver {
    void update(Character character);
}
```

And implementations for logging and warnings:

```java
public class ConsoleLoggerObserver implements IObserver {
    @Override
    public void update(Character character) {
        System.out.println("Observer Logger: Character " + character.getName() +
            " - Stats: " + character.getEffectiveStats() +
            " - State: " + character.getState().getName());
    }
}
```

Character implements ISubject and notifies when state changes:

```java
@Override
public void notifyObservers() {
    for (IObserver o : observers) {
        o.update(this);
    }
}

public void setState(ICharacterState state) {
    this.state = state;
    notifyObservers();
}
```

Output displays initial health 20, then on state change triggers logger showing updated stats with health 15 and state Poisoned, plus warning about low health under 30. Multiple observers reacted independently without Character knowing their details.

What we did: Created warrior, damaged it to low health, attached observers, changed to PoisonedState. Expected automatic logging and warnings via notifyObservers call. It worked - loose coupling between subject and observers, easy to add new reaction types without modifying Character.

![alt text](img/image%20copy%206.png)

## Conclusion

The behavioral patterns made our RPG project much more flexible. Strategy lets characters switch attack styles instantly, like a warrior learning magic. State gives characters different behaviors based on status, keeping the code clean instead of huge if-statements everywhere. Observer brings the system to life with automatic reactions, perfect for games where multiple systems need updates.

These patterns add flexibility because they separate "what to do" (operation) from "how to do it" (implementation). You can add new strategies or observers without touching existing code, following the open-closed principle. The project easily supports new attack types, status effects, or UI updates just by adding new classes.

The separation makes growing the code simple. Want chat logs? Add a ChatLoggerObserver. Need freezing? Add a FreezeState. The core character class stays unchanged, and new features plug in smoothly. This approach makes the codebase future-proof and team-friendly.

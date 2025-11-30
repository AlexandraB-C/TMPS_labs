# Behavioral Design Patterns Lab Report

## Theory

Behavioral design patterns are ways to handle how objects talk to each other and share responsibilities. They help make objects work together better without hardcoding everything inside them. These patterns are about communication and delegation.

The main idea is to make code more flexible so when things change, you don't have to rewrite everything.

Here are the key behavioral patterns:

**Chain of Responsibility** - Passes requests along a chain of handlers. 

**Command** - Turns a request into an object.

**Interpreter** - Defines a grammar and parses inputs. Good for scripting languages or config files.

**Iterator** - Provides a way to access elements without exposing internals.

**Mediator** - Reduces coupling by having objects communicate through a central hub.

**Memento** - Captures and restores object states without breaking encapsulation. Like save/load game progress.

**Observer** - Like a newsletter subscription. When something important happens to a character, all interested parties get notified automatically, without the character knowing who they are.

**State** - Like how moods change behavior. A hurt character might act differently (take extra damage) compared to a healthy one. The object changes behavior based on its current condition.

**Strategy** - Like choosing different tools for different jobs. A character can have different ways to attack, and you can swap them on the fly without changing the character itself.

**Template Method** - Defines a skeleton algorithm, lets subclasses fill in details.

**Visitor** - Adds new operations to existing classes without changing them.

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

The Strategy pattern lets us swap out character attack behaviors at runtime. Warriors start with melee attacks (strength-based damage), but can switch to magic if needed. The character class stays clean it just has a strategy field it delegates to.

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
strategy calculates melee damage using the attacker’s strength attribute and applies it directly to the target. The logic remains isolated inside the strategy instead of being hardcoded into Character, making it easy to swap to magic or ranged attacks without modifying core logic.

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
Delegates execution to whatever strategy is currently assigned. This prevents conditional branching (if warrior then damage = strength) and keeps combat scalable.

![alt text](img/image%20copy%202.png)

Characters attacked each other using different damage formulas depending on their strategy. The warrior dealt strength-based melee damage while the mage used intelligence-based magic. No code was changed during execution — only the assigned strategy mattered, proving runtime swapability and clean separation of combat logic.

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
This state reduces health every time stats are evaluated, simulating a passive effect. Instead of checking conditions manually each frame, the state object itself dictates behavior.

Character integrates state by calling applyStateEffects in getEffectiveStats:

```java
state.applyStateEffects(effective);
return effective;
```
Stats requests flow through the current state, so new effects can be added by creating a new class rather than editing Character.

![alt text](img/image%20copy%205.png)

During execution, the character began in NormalState with unchanged stats. Once switched to PoisonedState, each stats check reduced health automatically by 5, without additional method calls or conditions. The change was visible immediately in the output, showing that behavior depends entirely on active state rather than manual checks.

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
This observer listens for updates and logs useful character data. It works independently = no modifications to Character are required to handle logging.

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

Changing state automatically notifies subscribers. This keeps the subject completely unaware of who is listening, supporting loose coupling.

![alt text](img/image%20copy%206.png)

When the warrior’s health dropped, observers instantly responded. The logger printed new stats, while the low-health module raised a warning. Nothing inside Character was modified to support these reactions observers simply reacted on their own, showing clean modularity.

## Conclusion

Strategy, State and Observer helped make the character system flexible and easier to change. Strategy allowed us to switch attack types without editing the Character class. State moved status effects into separate classes so characters could change behavior at runtime. Observer let other classes react to character changes automatically which kept the core logic clean and not dependent on extra features.

The patterns we used work well because each one has a focused job. Strategy controls how damage is calculated. State controls conditions like poison. Observer controls how the system reacts to changes. This keeps the code easy to extend in the future since we can add new strategies or new states or new observers without rewriting old code.

Other behavioral patterns can also be added later if the project grows. Command could be useful if we ever want undoable actions or a turn based action queue. Chain of Responsibility could process damage in steps like armor then buffs then poison. Template Method could define a general combat turn with small parts that subclasses can change. Visitor could apply effects to many character types at once. These patterns are not needed right now but they could help if the game becomes more complex.

# Developer Guide

## Project Overview

This project is a Java implementation of the Blood Bowl board game developed as a programming exercise.

The goal is not only to reproduce the game rules but also to experiment with clean object-oriented design, domain-driven modeling, and test-driven development.

At the current stage, the project provides the foundations of a game engine and implements a limited subset of Blood Bowl mechanics.

---

## Current Implementation Status

### Implemented Rules

#### Board Management

* Players can be placed on the pitch.
* A square can contain at most one player.
* Player positions are tracked by the board.

#### Movement

* Players may move only to adjacent squares.
* Destination squares must be empty.
* Players cannot move to their current position.
* Movement points are tracked during the turn.
* A player cannot move beyond its Movement Allowance (MA).

#### Turn State

* Active players are tracked.
* Disabled players are tracked.
* Movement history is recorded.

#### Blocking Foundations

* A player may initiate a block only against an adjacent opponent.
* Block dice infrastructure exists.
* Combat dice rolls can be generated.

---

### Not Yet Implemented

The following Blood Bowl mechanics are planned but not currently available:

#### Core Gameplay

* Ball management
* Kick-off sequence
* Team turns
* Turnovers
* Touchdowns

#### Player Actions

* Dodge
* Rush / Going For It
* Pass
* Catch
* Hand-off
* Fouls

#### Combat

* Strength comparison
* Assists
* Multiple block dice
* Pushes
* Knockdowns
* Follow-up movement

#### Injuries

* Armour rolls
* Injury rolls
* Casualties
* Knocked-down state
* Stunned state

#### Teams

* Team rosters
* Races
* Skills
* Team rerolls

---

## Architecture

The project is organized around the game domain rather than the user interface.

### Models

Contains the main domain objects:

* Board
* Position
* Player
* TurnHistory

These classes represent the state of the game.

### Actions

Contains player actions:

* MoveAction
* FightAction

Actions are responsible for:

1. Validating whether an action is legal.
2. Producing the required dice rolls.
3. Applying the result to the game state.

### Dice System

The dice package contains:

* Dice definitions
* Dice roll generation
* Dice roll results
* Blood Bowl block dice abstractions

The objective is to keep all randomness isolated from game logic.

---

## Design Principles

### 1. Rules First

Game rules should be implemented independently from any user interface.

The engine must be usable from:

* Unit tests
* Command line applications
* Desktop applications
* Web applications
* AI simulations

### 2. Immutable Inputs

Actions should describe intentions.

Example:

```java
new MoveAction(player, destination);
```

The action validates itself and then modifies the game state through a well-defined API.

### 3. Test-Driven Development

Every new rule should be accompanied by tests.

Typical workflow:

1. Write a failing test.
2. Implement the rule.
3. Refactor.
4. Ensure all tests still pass.

### 4. Explicit Rule Modeling

Avoid generic boolean flags when a dedicated domain concept exists.

Prefer:

```java
PlayerState.STUNNED
```

over:

```java
player.setStunned(true);
```

---

## Suggested Roadmap

### Phase 1 – Complete Blocking

* Strength comparison
* Assists
* Multiple block dice
* Pushes
* Knockdowns
* Turnovers

### Phase 2 – Player States

* Standing
* Prone
* Stunned

### Phase 3 – Injury System

* Armour rolls
* Injury rolls
* Casualties

### Phase 4 – Ball Handling

* Pickup
* Pass
* Catch
* Hand-off

### Phase 5 – Full Turn Management

* Team turns
* Turnovers
* End-of-turn processing

### Phase 6 – Match Rules

* Kick-off
* Scoring
* Game clock
* Victory conditions

---

## Contribution Guidelines

When implementing a new rule:

1. Add unit tests first.
2. Keep the rule isolated from UI concerns.
3. Document important Blood Bowl assumptions.
4. Prefer small, focused commits.
5. Update this document when new mechanics are implemented.

---

## References

Useful references for contributors:

* Blood Bowl Rulebook (for rule interpretation)
* Existing project unit tests
* Project domain model documentation

When in doubt, favor readability and maintainability over premature optimization.

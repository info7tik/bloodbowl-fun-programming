# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**bloodbowl** is a Blood Bowl game implementation written in Java. It models a turn-based strategy game with players on a board, using an action-based system to manage game mechanics.

## Build Commands

All commands use Gradle and should be run from the project root.

**Common builds:**
- `./gradlew build` — compile and run all tests
- `./gradlew test` — run all tests
- `./gradlew test --tests ClassName` — run tests for a specific class (e.g., `BoardTest`)
- `./gradlew run` — run the main application
- `./gradlew jar` — build the JAR file
- `./gradlew clean` — clean build artifacts

## Architecture

### Package Structure

- **`fr.bloodbowl.models`** — Core domain models:
  - `Board` — manages the game board state, tracks player positions using a position-to-players map
  - `Position` — immutable 2D position (row, column)
  - `Player` — represents a player on the board with an identifier

- **`fr.bloodbowl.action`** — Action pattern implementation:
  - `Action` interface — defines the contract: `checkPrecondition()` and `execute()`
  - `ActionExecutor` — orchestrates action execution with precondition checks
  - `PlaceAction` — concrete action to place a player at a position
  - `FailedPreconditionException` — thrown when a precondition fails

- **`fr.bloodbowl`** — Application:
  - `App` — main entry point; demonstrates placing players on the board

### Key Patterns

**Action Pattern**: All game operations are modeled as `Action` implementations. Actions must define two methods:
- `checkPrecondition(Board)` — validate that the action can be executed (throws `FailedPreconditionException` if not)
- `execute(Board)` — modify board state

The `ActionExecutor` positions: call `checkPrecondition()` first, then `execute()` if it passes.

**Board State**: The `Board` tracks player positions using two maps:
- `occupiedSquares`: `Position → List<Player>` (multiple players can occupy the same square)
- `placedElements`: `String (player ID) → Player` (quick lookup by identifier)

## Dependencies

- **Lombok** (1.18.30) — reduces boilerplate with annotations like `@Data`, `@Slf4j`
- **SLF4J** (2.0.11) + **Logback** (1.5.3) — logging
- **JUnit Jupiter** — testing framework

Use Lombok annotations to reduce getter/setter/equals/hashCode boilerplate. Logging via `@Slf4j` or `LoggerFactory.getLogger()`.

## Testing

Tests are colocated with source in `app/src/test/java/fr/bloodbowl/`. Test utilities:
- `DataGenerator` — factory for test data
- `MockAction` — mock action for testing `ActionExecutor`

Tests use JUnit Jupiter and run via `./gradlew test`.

## Java Version

Java 21 is required (configured in `build.gradle.kts`).

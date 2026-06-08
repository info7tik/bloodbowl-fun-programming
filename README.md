# Blood Bowl Java

A Java implementation of the classic **Blood Bowl** board game, developed as a programming exercise.

## About Blood Bowl

Blood Bowl is a fantasy football board game created by Games Workshop. Players control teams composed of humans, orcs, elves, dwarves, and other fantasy races, competing in brutal matches where scoring touchdowns is only part of the challenge. Tackles, injuries, fouls, and unpredictable events are all part of the game.

Each turn, coaches must carefully manage player movement, blocking actions, ball handling, and team positioning while balancing risk and reward. Most actions require dice rolls, making strategy and probability management essential skills.

## In progress
The current implementation is essentially a Blood Bowl movement-and-action prototype.

## Implemented Rules
1. Board Positioning
Players can be placed on a board and each square can contain at most one player.

2. Adjacent-Square Movement
A player may move only to an adjacent square.

3. Movement Allowance (MA)
Each player has a movement attribute. A player may continue moving until the number of squares moved during the turn reaches that value. Additional movement is rejected.

4. Active / Inactive Players
Only active players may perform actions.

5. Blocking Eligibility
A player may declare a fight only if the opponent occupies an adjacent square.

6. Block Dice Infrastructure
A fight action prepares a special "fight roll" using one combat die.

## Partially Implemented Rules
1. Blocking Resolution
A FightAction exists and can:
* Validate adjacency
* Validate active player status
* Generate a combat die roll

However, the actual resolution method is empty.

## Rules Not Yet Implemented
I found no evidence of:
* Ball handling
* Pickup rolls
* Passing
* Catching
* Hand-offs
* Touchdowns
* Team turns
* Turnovers
* Dodge rolls
* Tackle zones
* Going For It (Rush)
* Skills
* Armour rolls
* Injury rolls
* Casualties
* Knocked Down / Stunned states
* Prone players
* Team rerolls
* Strength comparison
* Multiple block dice selection
* Assists
* Kick-off events
* Weather
* Team rosters or races

## Disclaimer

This project is an independent programming exercise inspired by the Blood Bowl board game. Blood Bowl and all related intellectual property are trademarks of Games Workshop.

package fr.bloodbowl.action;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.dices.IntegerGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiceRoller {
    private final IntegerGenerator generator;
    private final int minNumberOnCubes = 1;

    public DieRollResult execute(DieRoll roll) {
        return new DieRollResult(
                generator.generate(roll.getDiceNumber(), minNumberOnCubes, roll.getCubeNumbers()), roll.isFightDices());
    }
}

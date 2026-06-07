package fr.bloodbowl.action;

import java.util.ArrayList;
import java.util.Collections;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;

public class DiceRoller {
    public DieRollResult execute(DieRoll roll) {
        return new DieRollResult(Collections.unmodifiableList(new ArrayList<>()), roll.isFightDices());
    }
}

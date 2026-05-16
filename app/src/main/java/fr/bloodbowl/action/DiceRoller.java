package fr.bloodbowl.action;

import java.util.List;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollFactory;
import fr.bloodbowl.dices.DieRollResult;

public class DiceRoller {
    public DieRollResult execute(List<DieRoll> rolls) {
        return DieRollFactory.successfulResult();
    }
}

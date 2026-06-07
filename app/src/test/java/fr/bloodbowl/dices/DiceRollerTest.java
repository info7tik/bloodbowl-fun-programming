package fr.bloodbowl.dices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.action.DiceRoller;

public class DiceRollerTest {
    private DiceRoller roller = new DiceRoller();
    int dieScore = 4;

    @Test
    void rollOneSixCubeDice() {
        int numberOfDices = 1;

        DieRollResult result = roller.execute(DieRollFactory.sixCubesRoll(numberOfDices), MockRandomInteger(dieScore));

        assertEquals(result.getResults().size(), numberOfDices);
        assertEquals(result.getResults().getFirst(), dieScore);
    }

    @Test
    void rollTwoHeightCubeDices() {
        int numberOfDices = 2;

        DieRollResult result = roller
                .execute(DieRollFactory.heightCubesRoll(numberOfDices), MockRandomInteger(dieScore));

        assertEquals(result.getResults().size(), numberOfDices);
        assertEquals(result.sum(), dieScore * numberOfDices);
    }

    @Test
    void rollNoDices() {
        DieRollResult result = roller.execute(DieRollFactory.noRoll(), MockRandomInteger(dieScore));

        assertTrue(result.getResults().isEmpty());
    }
}

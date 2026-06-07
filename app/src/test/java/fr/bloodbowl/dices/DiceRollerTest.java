package fr.bloodbowl.dices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.action.DiceRoller;
import fr.bloodbowl.mock.MockRandomInteger;

public class DiceRollerTest {
    private MockRandomInteger mockRandom = new MockRandomInteger();
    private DiceRoller roller = new DiceRoller(mockRandom);
    int dieScore = 4;

    @Test
    void rollOneSixCubeDice() {
        int numberOfDices = 1;
        mockRandom.setScore(dieScore);

        DieRollResult result = roller.execute(DieRollFactory.sixCubesRoll(numberOfDices));

        assertEquals(result.getResults().size(), numberOfDices);
        assertEquals(result.getResults().getFirst(), dieScore);
        assertFalse(result.isFightDices());
    }

    @Test
    void rollTwoHeightCubeDices() {
        int numberOfDices = 2;
        mockRandom.setScore(dieScore);

        DieRollResult result = roller.execute(DieRollFactory.heightCubesRoll(numberOfDices));

        assertEquals(result.getResults().size(), numberOfDices);
        assertEquals(result.sum(), dieScore * numberOfDices);
        assertFalse(result.isFightDices());
    }

    @Test
    void rollFightDices() {
        DieRollResult result = roller.execute(DieRollFactory.fightRoll(3));

        assertTrue(result.isFightDices());
    }

    @Test
    void rollNoDices() {
        DieRollResult result = roller.execute(DieRollFactory.noRoll());

        assertTrue(result.getResults().isEmpty());
    }
}

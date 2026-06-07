package fr.bloodbowl.dices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class DieRollTest {
    @Test
    void dieRollEqualsMustBeTrue() {
        DieRoll roll1 = new DieRoll(3, 4, false);
        DieRoll roll2 = new DieRoll(3, 4, false);
        assertEquals(roll1, roll2);
    }

    @Test
    void dieRollsWithDifferentCubeNumbersAreNotEquals() {
        DieRoll roll1 = new DieRoll(3, 4, false);
        DieRoll roll2 = new DieRoll(5, 4, false);
        assertNotEquals(roll1, roll2);
    }

    @Test
    void dieRollsWithDifferentDiceNumbersAreNotEquals() {
        DieRoll roll1 = new DieRoll(3, 4, false);
        DieRoll roll2 = new DieRoll(3, 5, false);
        assertNotEquals(roll1, roll2);
    }

    @Test
    void fightDieRollsAreNotEqualsToOtherRolls() {
        DieRoll roll1 = new DieRoll(3, 4, false);
        DieRoll roll2 = new DieRoll(3, 5, true);
        assertNotEquals(roll1, roll2);
    }
}

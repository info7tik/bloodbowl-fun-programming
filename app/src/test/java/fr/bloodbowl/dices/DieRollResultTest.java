package fr.bloodbowl.dices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.dices.FightCubeFactory.FightCube;

class DieRollResultTest {

    @Test
    void convertDieResultToFightResult() {
        List<Integer> allSixCubes = List.of(1, 2, 3, 4, 5, 6);
        DieRollResult result = new DieRollResult(allSixCubes, true);
        List<FightCube> fightResults = result.getFightResults();
        assertEquals(fightResults.size(), allSixCubes.size());
        assertEquals(FightCubeFactory.build(allSixCubes.get(0)), fightResults.get(0));
        assertEquals(FightCubeFactory.build(allSixCubes.get(1)), fightResults.get(1));
        assertEquals(FightCubeFactory.build(allSixCubes.get(2)), fightResults.get(2));
        assertEquals(FightCubeFactory.build(allSixCubes.get(3)), fightResults.get(3));
        assertEquals(FightCubeFactory.build(allSixCubes.get(4)), fightResults.get(4));
        assertEquals(FightCubeFactory.build(allSixCubes.get(5)), fightResults.get(5));
        List<FightCube> distinctResults = fightResults.stream().distinct().toList();
        assertEquals(5, distinctResults.size());
    }

    @Test
    void convertNonFightDicesToFightResultMustFail() {
        DieRollResult result = new DieRollResult(List.of(2), false);
        assertThrows(IllegalStateException.class, () -> result.getFightResults());
    }
}

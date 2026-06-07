package fr.bloodbowl.dices;

import java.util.List;

import fr.bloodbowl.dices.FightCubeFactory.FightCube;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DieRollResult {
    @Getter
    private final List<Integer> results;
    @Getter
    private final boolean fightDices;

    public int sum() {
        return results.stream().mapToInt(i -> i.intValue()).sum();
    }

    public List<FightCube> getFightResults() {
        if (isFightDices()) {
            return results.stream().map(dice -> FightCubeFactory.build(dice)).toList();
        } else {
            throw new IllegalStateException("can not convert to fight results (isFightDices: " + fightDices + ")");
        }
    }
}

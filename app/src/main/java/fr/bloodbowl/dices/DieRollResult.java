package fr.bloodbowl.dices;

import java.util.List;

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
}

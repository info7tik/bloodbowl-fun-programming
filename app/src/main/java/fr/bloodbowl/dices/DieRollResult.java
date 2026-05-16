package fr.bloodbowl.dices;

import lombok.Getter;

public class DieRollResult {
    @Getter
    private final int result;
    private final DieComparator comparator;
    private final int target;

    DieRollResult(int result, DieRoll roll) {
        this.result = result;
        comparator = roll.getComparator();
        target = roll.getTarget();
    }

    public boolean isSuccessful() {
        return true;
    }
}

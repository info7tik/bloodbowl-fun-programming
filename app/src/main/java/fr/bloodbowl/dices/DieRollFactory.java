package fr.bloodbowl.dices;

public class DieRollFactory {
    public static DieRoll sixCubesRoll(DieComparator comparator, int target) {
        return new DieRoll(6, comparator, target);
    }

    public static DieRoll heightCubesRoll(DieComparator comparator, int target) {
        return new DieRoll(8, comparator, target);
    }

    public static DieRoll fightRoll() {
        return new DieRoll(6, DieComparator.NOT_APPLICAPLE, 0);
    }

    public static DieRollResult rollResult(int result, DieRoll roll) {
        return new DieRollResult(result, roll);
    }

    public static DieRollResult successfulResult() {
        return new DieRollResult(0, new DieRoll(0, DieComparator.NOT_APPLICAPLE, 0));

    }
}

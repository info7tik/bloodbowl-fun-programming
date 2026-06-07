package fr.bloodbowl.dices;

public class DieRollFactory {
    public static DieRoll sixCubesRoll(int numberOfDices) {
        return new DieRoll(6, numberOfDices, false);
    }

    public static DieRoll heightCubesRoll(int numberOfDices) {
        return new DieRoll(8, numberOfDices, false);
    }

    public static DieRoll fightRoll(int numberOfDices) {
        return new DieRoll(6, numberOfDices, true);
    }

    public static DieRoll noRoll() {
        return new DieRoll(6, 0, false);
    }
}

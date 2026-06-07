package fr.bloodbowl.dices;

public class FightCubeFactory {
    public enum FightCube {
        PUSHED, ATTACKER_DOWN, BOTH_DOWN, DEFENDER_STUMBLES, DEFENDER_DOWN

    }

    public static FightCube build(int sixCubeNumber) {
        switch (sixCubeNumber) {
            case 1, 4:
                return FightCube.PUSHED;
            case 2:
                return FightCube.BOTH_DOWN;
            case 3:
                return FightCube.ATTACKER_DOWN;
            case 5:
                return FightCube.DEFENDER_DOWN;
            case 6:
                return FightCube.DEFENDER_STUMBLES;
            default:
                throw new IllegalArgumentException("die number " + sixCubeNumber + " is not a valid fight values");
        }
    }

}

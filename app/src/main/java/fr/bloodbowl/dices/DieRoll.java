package fr.bloodbowl.dices;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DieRoll {
    @Getter
    private final int cubeNumbers;
    @Getter
    private final int diceNumber;
    @Getter
    private final boolean fightDices;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DieRoll) {
            DieRoll casted = (DieRoll) obj;
            return cubeNumbers == casted.cubeNumbers
                    && diceNumber == casted.diceNumber
                    && fightDices == casted.fightDices;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return cubeNumbers + diceNumber + (fightDices ? 1 : 0);
    }
}

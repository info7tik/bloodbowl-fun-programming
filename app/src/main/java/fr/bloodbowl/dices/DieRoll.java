package fr.bloodbowl.dices;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DieRoll {
    @Getter
    private final int cubeNumbers;
    @Getter
    private final DieComparator comparator;
    @Getter
    private final int target;
}

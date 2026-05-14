package fr.bloodbowl.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Player {
    @Getter
    private final String identifier;
    @Getter
    private final int movement;

    @Override
    public String toString() {
        return "P_" + identifier;
    }
}

package fr.bloodbowl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Player {
    @Getter
    private final String identifier;
    @Getter
    private final int movement;

    @Override
    public String toString() {
        return "P_" + identifier;
    }
}

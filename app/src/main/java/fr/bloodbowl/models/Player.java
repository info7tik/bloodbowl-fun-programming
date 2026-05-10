package fr.bloodbowl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Player {
    @Getter
    private String identifier;

    @Override
    public String toString() {
        return "P_" + identifier;
    }
}

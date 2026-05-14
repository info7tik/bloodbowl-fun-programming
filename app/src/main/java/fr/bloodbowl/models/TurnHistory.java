package fr.bloodbowl.models;

import java.util.HashMap;
import java.util.Map;

public class TurnHistory {
    private Map<String, Integer> activePlayers = new HashMap<>();

    public boolean isActive(String identifier) {
        return activePlayers.containsKey(identifier);
    }

    public void addActivePlayer(String identifier) {
        if (!activePlayers.containsKey(identifier))
            activePlayers.put(identifier, 0);
    }

    public void registerMovement(String identifier) {
        ensurePlayerExists(identifier);
        int oneSquareMove = 1;
        registerMovement(identifier, oneSquareMove);
    }

    public void registerMovement(String identifier, int quantity) {
        ensurePlayerExists(identifier);
        int currentMovement = activePlayers.get(identifier);
        activePlayers.put(identifier, currentMovement + quantity);
    }

    public int getMovement(String identifier) {
        ensurePlayerExists(identifier);
        return activePlayers.get(identifier);
    }

    private void ensurePlayerExists(String identifier) {
        if (!activePlayers.containsKey(identifier)) {
            throw new IllegalArgumentException("player " + identifier + " is not an active players");
        }
    }
}

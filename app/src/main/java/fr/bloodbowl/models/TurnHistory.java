package fr.bloodbowl.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnHistory {
    private List<String> activePlayers = new ArrayList<>();
    private List<String> disabledPlayers = new ArrayList<>();
    private Map<String, Integer> movedPlayers = new HashMap<>();

    public boolean isActive(String identifier) {
        return activePlayers.contains(identifier);
    }

    public void addActivePlayer(String identifier) {
        if (disabledPlayers.contains(identifier)) {
            throw new IllegalArgumentException(
                    "can not activate player '" + identifier + "'' because he is in disabled players");
        }
        if (!activePlayers.contains(identifier)) {
            activePlayers.add(identifier);
            movedPlayers.put(identifier, 0);
        }
    }

    public void disablePlayer(String identifier) {
        ensurePlayerExists(identifier);
        activePlayers.remove(identifier);
        disabledPlayers.add(identifier);
    }

    public void registerMovement(String identifier) {
        ensurePlayerExists(identifier);
        int oneSquareMove = 1;
        registerMovement(identifier, oneSquareMove);
    }

    public void registerMovement(String identifier, int quantity) {
        ensurePlayerExists(identifier);
        int currentMovement = movedPlayers.get(identifier);
        movedPlayers.put(identifier, currentMovement + quantity);
    }

    public int getMovement(String identifier) {
        ensurePlayerExists(identifier);
        return movedPlayers.get(identifier);
    }

    private void ensurePlayerExists(String identifier) {
        if (!activePlayers.contains(identifier)) {
            throw new IllegalArgumentException("player " + identifier + " is not an active players");
        }
    }
}

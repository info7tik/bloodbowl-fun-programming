package fr.bloodbowl.models;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Position, Player> occupiedPositions = new HashMap<>();
    private Map<String, Position> placedPlayers = new HashMap<>();

    public void placeAt(Position position, Player player) {
        if (placedPlayers.containsKey(player.getIdentifier())) {
            throw new IllegalArgumentException(player + " is already in the board");
        }
        occupiedPositions.put(position, player);
        placedPlayers.put(player.getIdentifier(), position);
    }

    public void remove(String playerId) {
        if (placedPlayers.containsKey(playerId)) {
            Position position = placedPlayers.remove(playerId);
            occupiedPositions.remove(position);
        }

    }

    public Player get(Position position) {
        if (occupiedPositions.containsKey(position))
            return occupiedPositions.get(position);
        throw new IllegalArgumentException("no player at " + position);
    }

    public Position get(String playerId) {
        if (placedPlayers.containsKey(playerId))
            return placedPlayers.get(playerId);
        throw new IllegalArgumentException("no player with id " + playerId);
    }

    public boolean has(String playerId) {
        return placedPlayers.containsKey(playerId);
    }

    public boolean isOccupied(Position position) {
        return occupiedPositions.containsKey(position);
    }

    public boolean isEmpty(Position position) {
        return !isOccupied(position);
    }
}

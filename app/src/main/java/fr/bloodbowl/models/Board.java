package fr.bloodbowl.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Position, List<Player>> occupiedSquares = new HashMap<>();
    private Map<String, Position> placedElements = new HashMap<>();

    public void placeAt(Position position, Player player) {
        if (placedElements.containsKey(player.getIdentifier())) {
            throw new IllegalArgumentException("element " + player + " is already in the board");
        }
        if (!occupiedSquares.containsKey(position)) {
            occupiedSquares.put(position, new ArrayList<>());
        }
        occupiedSquares.get(position).add(player);
        placedElements.put(player.getIdentifier(), position);
    }

    public void remove(Player player) {
        for (List<Player> elements : occupiedSquares.values()) {
            if (elements.contains(player)) {
                elements.remove(player);
                placedElements.remove(player.getIdentifier());
                return;
            }
        }
    }

    public Player get(Position position) {
        if (occupiedSquares.containsKey(position))
            return occupiedSquares.get(position).get(0);
        throw new IllegalArgumentException("no element at " + position);
    }

    public Position get(String playerId) {
        if (placedElements.containsKey(playerId))
            return placedElements.get(playerId);
        throw new IllegalArgumentException("no element with id " + playerId);
    }

    public boolean has(String playerId) {
        return placedElements.containsKey(playerId);
    }

    public boolean isOccupied(Position position) {
        return occupiedSquares.containsKey(position) && !occupiedSquares.get(position).isEmpty();
    }

    public boolean isEmpty(Position position) {
        return !isOccupied(position);
    }
}

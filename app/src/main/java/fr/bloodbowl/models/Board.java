package fr.bloodbowl.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Coordinate, List<Player>> occupiedSquares = new HashMap<>();
    private Map<String, Coordinate> placedElements = new HashMap<>();

    public void placeAt(Coordinate coord, Player player) {
        if (placedElements.containsKey(player.getIdentifier())) {
            throw new IllegalArgumentException("element " + player + " is already in the board");
        }
        if (!occupiedSquares.containsKey(coord)) {
            occupiedSquares.put(coord, new ArrayList<>());
        }
        occupiedSquares.get(coord).add(player);
        placedElements.put(player.getIdentifier(), coord);
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

    public Player get(Coordinate coord) {
        if (occupiedSquares.containsKey(coord))
            return occupiedSquares.get(coord).get(0);
        throw new IllegalArgumentException("no element at " + coord);
    }

    public Coordinate get(String playerId) {
        if (placedElements.containsKey(playerId))
            return placedElements.get(playerId);
        throw new IllegalArgumentException("no element with id " + playerId);
    }

    public boolean has(String playerId) {
        return placedElements.containsKey(playerId);
    }

    public boolean isOccupied(Coordinate coord) {
        return occupiedSquares.containsKey(coord) && !occupiedSquares.get(coord).isEmpty();
    }

    public boolean isEmpty(Coordinate coord) {
        return !isOccupied(coord);
    }
}

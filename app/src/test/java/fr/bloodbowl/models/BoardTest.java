package fr.bloodbowl.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.testlib.DataGenerator;

public class BoardTest {
    private Board board = new Board();

    @Test()
    void getAtEmptySquare() {
        assertThrows(IllegalArgumentException.class, () -> board.get(new Coordinate(3, 4)));
    }

    @Test
    void placeAt() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertEquals(player, board.get(coord));
    }

    @Test
    void placeAtDifferentCoordinateObjects() {
        int row = 3;
        int column = 4;
        Player player = DataGenerator.player1();
        board.placeAt(new Coordinate(row, column), player);
        assertEquals(player, board.get(new Coordinate(row, column)));
    }

    @Test
    void placeAtSameElementAtSameCoordinate() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(coord, player));
    }

    @Test
    void placeAtSameElementAtDifferentCoordinate() {
        Coordinate coord1 = new Coordinate(3, 4);
        Coordinate coord2 = new Coordinate(3, 6);
        Player player = DataGenerator.player1();
        board.placeAt(coord1, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(coord2, player));
    }

    @Test()
    void isEmpty() {
        Coordinate coord = new Coordinate(3, 4);
        assertTrue(board.isEmpty(coord));
        board.placeAt(coord, DataGenerator.player1());
        assertFalse(board.isEmpty(coord));
    }

    @Test()
    void has() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        assertFalse(board.has(player));
        board.placeAt(coord, player);
        assertTrue(board.has(player));
        assertFalse(board.has(DataGenerator.player2()));
    }

    @Test()
    void isOccupied() {
        Coordinate coord = new Coordinate(3, 4);
        assertFalse(board.isOccupied(coord));
        board.placeAt(coord, DataGenerator.player1());
        assertTrue(board.isOccupied(coord));
    }

    @Test
    void removeAt() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertTrue(board.isOccupied(coord));
        board.remove(player);
        assertFalse(board.isOccupied(coord));
    }

    @Test
    void removeAtWithNotFoundElement() {
        assertThrows(IllegalArgumentException.class, () -> board.remove(DataGenerator.player1()));
    }
}

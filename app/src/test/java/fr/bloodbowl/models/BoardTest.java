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
    void getElementFromEmptySquaresMustThrowException() {
        assertThrows(IllegalArgumentException.class, () -> board.get(new Coordinate(3, 4)));
    }

    @Test()
    void getPositionFromEmptySquaresMustThrowException() {
        assertThrows(IllegalArgumentException.class, () -> board.get("not_existing"));
    }

    @Test()
    void getPositionOfExistingPlayer() {
        Coordinate playerPosition = new Coordinate(3, 4);
        Board boardWithPlayer = DataGenerator.boardWithPlayer1(playerPosition);
        assertEquals(playerPosition, boardWithPlayer.get(DataGenerator.player1().getIdentifier()));
    }

    @Test
    void placeElementsInEmptySquare() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertEquals(player, board.get(coord));
    }

    @Test
    void ensurePlaceAtCanManageEqualCoordinates() {
        int row = 3;
        int column = 4;
        Player player = DataGenerator.player1();
        board.placeAt(new Coordinate(row, column), player);
        assertEquals(player, board.get(new Coordinate(row, column)));
    }

    @Test
    void placeElementsInOccupiedSquareMustThrowException() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(coord, player));
    }

    @Test
    void placeTheSameElementTwiceMustThrowException() {
        Coordinate coord1 = new Coordinate(3, 4);
        Coordinate coord2 = new Coordinate(3, 6);
        Player player = DataGenerator.player1();
        board.placeAt(coord1, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(coord2, player));
    }

    @Test()
    void detectEmptySquares() {
        Coordinate coord = new Coordinate(3, 4);
        assertTrue(board.isEmpty(coord));
        board.placeAt(coord, DataGenerator.player1());
        assertFalse(board.isEmpty(coord));
    }

    @Test()
    void detectTheElementIsInTheSquare() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        assertFalse(board.has(player));
        board.placeAt(coord, player);
        assertTrue(board.has(player));
        assertFalse(board.has(DataGenerator.player2()));
    }

    @Test()
    void detectOccupiedSquares() {
        Coordinate coord = new Coordinate(3, 4);
        assertFalse(board.isOccupied(coord));
        board.placeAt(coord, DataGenerator.player1());
        assertTrue(board.isOccupied(coord));
    }

    @Test
    void removeElementsFromTheBoard() {
        Coordinate coord = new Coordinate(3, 4);
        Player player = DataGenerator.player1();
        board.placeAt(coord, player);
        assertTrue(board.isOccupied(coord));
        board.remove(player);
        assertFalse(board.isOccupied(coord));
    }

    @Test
    void removeNotExistingElementsFromTheBoardDoNothing() {
        board.remove(DataGenerator.player1());
    }
}

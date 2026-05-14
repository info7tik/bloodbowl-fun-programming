package fr.bloodbowl.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.testlib.DataBuilder;

public class BoardTest {
    private Board board = DataBuilder.emptyBoard();

    @Test()
    void getElementFromEmptySquaresMustThrowException() {
        assertThrows(IllegalArgumentException.class, () -> board.get(new Position(3, 4)));
    }

    @Test()
    void getPositionFromEmptySquaresMustThrowException() {
        assertThrows(IllegalArgumentException.class, () -> board.get("not_existing"));
    }

    @Test()
    void getPositionOfExistingPlayer() {
        Position playerPosition = new Position(3, 4);
        Board boardWithPlayer = DataBuilder.boardWithPlayer1(playerPosition);
        assertEquals(playerPosition, boardWithPlayer.get(DataBuilder.player1().getIdentifier()));
    }

    @Test
    void placeElementsInEmptySquare() {
        Position position = new Position(3, 4);
        Player player = DataBuilder.player1();
        board.placeAt(position, player);
        assertEquals(player, board.get(position));
    }

    @Test
    void ensurePlaceAtCanManageEqualPositionWithDifferentObject() {
        int row = 3;
        int column = 4;
        Player player = DataBuilder.player1();
        board.placeAt(new Position(row, column), player);
        assertEquals(player, board.get(new Position(row, column)));
    }

    @Test
    void placeElementsInOccupiedSquareMustThrowException() {
        Position position = new Position(3, 4);
        Player player = DataBuilder.player1();
        board.placeAt(position, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(position, player));
    }

    @Test
    void placeTheSameElementTwiceMustThrowException() {
        Position position1 = new Position(3, 4);
        Position position2 = new Position(3, 6);
        Player player = DataBuilder.player1();
        board.placeAt(position1, player);
        assertThrows(IllegalArgumentException.class, () -> board.placeAt(position2, player));
    }

    @Test()
    void detectEmptySquares() {
        Position position = new Position(3, 4);
        assertTrue(board.isEmpty(position));
        board.placeAt(position, DataBuilder.player1());
        assertFalse(board.isEmpty(position));
    }

    @Test()
    void detectTheElementIsInTheSquare() {
        Position position = new Position(3, 4);
        Player player = DataBuilder.player1();
        assertFalse(board.has(player.getIdentifier()));
        board.placeAt(position, player);
        assertTrue(board.has(player.getIdentifier()));
        assertFalse(board.has(DataBuilder.player2().getIdentifier()));
    }

    @Test()
    void detectOccupiedSquares() {
        Position position = new Position(3, 4);
        assertFalse(board.isOccupied(position));
        board.placeAt(position, DataBuilder.player1());
        assertTrue(board.isOccupied(position));
    }

    @Test
    void removeElementsFromTheBoard() {
        Position position = new Position(3, 4);
        Player player = DataBuilder.player1();
        board.placeAt(position, player);
        assertTrue(board.isOccupied(position));
        board.remove(player.getIdentifier());
        assertFalse(board.isOccupied(position));
    }

    @Test
    void removeNotExistingElementsFromTheBoardDoNothing() {
        board.remove(DataBuilder.player1().getIdentifier());
    }
}

package fr.bloodbowl.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position = new Position(3, 4);

    @Test
    void isAdjacentWithAdjacentVerticalSquareMustBeTrue() {
        assertTrue(position.isAdjacent(new Position(position.getRow(), position.getColumn() + 1)));
        assertTrue(position.isAdjacent(new Position(position.getRow(), position.getColumn() - 1)));
    }

    @Test
    void isAdjacentWithAdjacentHorizontalSquareMustBeTrue() {
        assertTrue(position.isAdjacent(new Position(position.getRow() + 1, position.getColumn())));
        assertTrue(position.isAdjacent(new Position(position.getRow() - 1, position.getColumn())));
    }

    @Test
    void isAdjacentWithAdjacentDiagonalSquareMustBeTrue() {
        assertTrue(position.isAdjacent(new Position(position.getRow() + 1, position.getColumn() + 1)));
        assertTrue(position.isAdjacent(new Position(position.getRow() + 1, position.getColumn() - 1)));
        assertTrue(position.isAdjacent(new Position(position.getRow() - 1, position.getColumn() + 1)));
        assertTrue(position.isAdjacent(new Position(position.getRow() - 1, position.getColumn() - 1)));
    }

    @Test
    void isAdjacentWithTooFarAwayPositionMustBeFalse() {
        assertFalse(position.isAdjacent(new Position(position.getRow() + 2, position.getColumn())));
        assertFalse(position.isAdjacent(new Position(position.getRow() + 1, position.getColumn() - 2)));
    }

    @Test
    void isAdjacentWithSamePositionMustBeFalse() {
        assertFalse(position.isAdjacent(new Position(position.getRow(), position.getColumn())));
    }
}

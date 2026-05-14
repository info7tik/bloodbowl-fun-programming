package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.testlib.DataBuilder;

public class PlaceActionTest {
    private Board board = DataBuilder.emptyBoard();

    @Test
    void checkPreconditionWithEmptyBoard() throws FailedPreconditionException {
        Coordinate coord = new Coordinate(3, 4);
        PlaceAction action = new PlaceAction(DataBuilder.player1(), coord);
        action.checkPrecondition(board);
    }

    @Test
    void checkPreconditionWithPlayerAlreadyPlaced() {
        Coordinate coord = new Coordinate(3, 4);
        board.placeAt(coord, DataBuilder.player1());
        PlaceAction action = new PlaceAction(DataBuilder.player1(), coord);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void checkPreconditionWithPlayerAlreadyAtCoordinate() {
        Coordinate coord = new Coordinate(3, 4);
        board.placeAt(coord, DataBuilder.player2());
        PlaceAction action = new PlaceAction(DataBuilder.player1(), coord);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void execute() {
        Coordinate coord = new Coordinate(3, 4);
        PlaceAction action = new PlaceAction(DataBuilder.player1(), coord);
        action.execute(board);
        assertEquals(action.getPlayer(), board.get(coord));
    }
}

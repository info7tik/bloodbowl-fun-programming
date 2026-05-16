package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.dices.DieRollFactory;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.testlib.DataBuilder;

public class PlaceActionTest {
    private Board board = DataBuilder.emptyBoard();

    @Test
    void checkPreconditionWhenPlacingThePlayerMustSucceed() throws FailedPreconditionException {
        Position position = new Position(3, 4);
        PlaceAction action = new PlaceAction(DataBuilder.player1(), position);
        action.checkPrecondition(board);
    }

    @Test
    void checkPreconditionWhenPlacingPlayersAlreadyPlaced() {
        Position position = new Position(3, 4);
        board.placeAt(position, DataBuilder.player1());
        PlaceAction action = new PlaceAction(DataBuilder.player1(), position);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void checkPreconditionWhenPlacingThePlayerAtAnOccupiedSquare() {
        Position position = new Position(3, 4);
        board.placeAt(position, DataBuilder.player2());
        PlaceAction action = new PlaceAction(DataBuilder.player1(), position);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void executePlaceThePlayerOnTheBoard() {
        Position position = new Position(3, 4);
        PlaceAction action = new PlaceAction(DataBuilder.player1(), position);
        action.execute(board, DataBuilder.emptyHistory(), DieRollFactory.successfulResult());
        assertEquals(action.getPlayer(), board.get(position));
    }
}

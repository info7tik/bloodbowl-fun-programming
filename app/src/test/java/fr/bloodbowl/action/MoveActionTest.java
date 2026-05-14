package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.TurnHistory;
import fr.bloodbowl.testlib.DataBuilder;

public class MoveActionTest {
    private final Coordinate player1Position = new Coordinate(3, 4);
    private final Board board = DataBuilder.boardWithPlayer1(player1Position);
    private final TurnHistory history = DataBuilder.emptyHistory();

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentVerticalSquare() throws FailedPreconditionException {
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow(), player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow(), player1Position.getColumn() - 1);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentHorizontalSquare() throws FailedPreconditionException {
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() + 1, player1Position.getColumn());
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() - 1, player1Position.getColumn());
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentDiagonalSquare() throws FailedPreconditionException {
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() + 1, player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() + 1, player1Position.getColumn() - 1);
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() - 1, player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToWithSuccess(player1Position.getRow() - 1, player1Position.getColumn() - 1);
    }

    private void checkPreconditionWhenMovingToWithSuccess(int destRow, int destColumn)
            throws FailedPreconditionException {
        Coordinate destination = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), destination);
        action.checkPrecondition(board);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToDistantSquareMustThrowException() {
        this.checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn() + 2);
        this.checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn() - 2);
        this.checkPreconditionWithErrorWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn() - 2);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToTheSameSquareMustThrowException() {
        this.checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn());
    }

    @Test
    void checkPreconditionWhenMovingPlayersThatDoesNotExistMustThrowException() {
        Coordinate distantPosition = new Coordinate(3, 4);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(new Board()));
    }

    private void checkPreconditionWithErrorWhenMovingTo(int destRow, int destColumn) {
        Coordinate distantPosition = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }
}

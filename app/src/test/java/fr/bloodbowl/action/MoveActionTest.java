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
    private final TurnHistory emptyHistory = DataBuilder.emptyHistory();
    private final TurnHistory activePlayerHistory = DataBuilder.historyWithActivePlayer1();

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
        checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn() + 2);
        checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn() - 2);
        checkPreconditionWithErrorWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn() - 2);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToTheSameSquareMustThrowException() {
        checkPreconditionWithErrorWhenMovingTo(player1Position.getRow(), player1Position.getColumn());
    }

    @Test
    void checkPreconditionWhenMovingPlayersThatDoesNotExistMustThrowException() {
        Coordinate distantPosition = new Coordinate(3, 4);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(DataBuilder.emptyBoard()));
    }

    private void checkPreconditionWithErrorWhenMovingTo(int destRow, int destColumn) {
        Coordinate distantPosition = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void checkStateForPlayersNotInHistoryThrowsException() {
        MoveAction action = new MoveAction(DataBuilder.player1(), new Coordinate(3, 5));
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }

    @Test
    void checkStateForPlayersWithEnoughMovementMustSucceed() throws FailedPreconditionException {
        MoveAction action = new MoveAction(DataBuilder.player1(), new Coordinate(3, 5));
        action.checkState(activePlayerHistory);
    }

    @Test
    void checkStateForPlayersWithExhaustedMovementMustFail() {
        MoveAction action = new MoveAction(DataBuilder.player1(), new Coordinate(3, 5));
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }
}

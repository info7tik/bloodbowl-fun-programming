package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.TurnHistory;
import fr.bloodbowl.testlib.DataBuilder;

public class MoveActionTest {
    private final Coordinate player1Position = new Coordinate(3, 4);
    private final Board boardWithPlayer = DataBuilder.boardWithPlayer1(player1Position);
    private final TurnHistory emptyHistory = DataBuilder.emptyHistory();
    private final TurnHistory activePlayerHistory = DataBuilder.historyWithActivePlayer1();

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentVerticalSquareMustSucceed() throws FailedPreconditionException {
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow(), player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow(), player1Position.getColumn() - 1);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentHorizontalSquareMustSucceed() throws FailedPreconditionException {
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() + 1, player1Position.getColumn());
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() - 1, player1Position.getColumn());
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentDiagonalSquareMustSucceed() throws FailedPreconditionException {
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() + 1, player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() + 1, player1Position.getColumn() - 1);
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() - 1, player1Position.getColumn() + 1);
        checkPreconditionWhenMovingToMustSucceed(player1Position.getRow() - 1, player1Position.getColumn() - 1);
    }

    private void checkPreconditionWhenMovingToMustSucceed(int destRow, int destColumn)
            throws FailedPreconditionException {
        Coordinate destination = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), destination);
        action.checkPrecondition(boardWithPlayer);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToDistantSquareMustFail() {
        checkPreconditionMustFailWhenMovingTo(player1Position.getRow(), player1Position.getColumn() + 2);
        checkPreconditionMustFailWhenMovingTo(player1Position.getRow(), player1Position.getColumn() - 2);
        checkPreconditionMustFailWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn() - 2);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToTheSameSquareMustFail() {
        checkPreconditionMustFailWhenMovingTo(player1Position.getRow(), player1Position.getColumn());
    }

    private void checkPreconditionMustFailWhenMovingTo(int destRow, int destColumn) {
        Coordinate distantPosition = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkPreconditionWhenMovingPlayersThatDoesNotExistMustFail() {
        Coordinate distantPosition = new Coordinate(3, 4);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(DataBuilder.emptyBoard()));
    }

    @Test
    void checkPreconditionWhenMovingToOccupiedSquaresMustFail() {
        Player player1 = DataBuilder.player1();
        Coordinate occupiedPosition = new Coordinate(player1Position.getRow(), player1Position.getColumn() + 1);
        boardWithPlayer.placeAt(occupiedPosition, DataBuilder.player2());
        MoveAction action = new MoveAction(player1, occupiedPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkStateForPlayersNotInHistoryMustFail() {
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
        Player player1 = DataBuilder.player1();
        MoveAction action = new MoveAction(player1, new Coordinate(3, 5));
        activePlayerHistory.registerMovement(player1.getIdentifier(), player1.getMovement());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }
}

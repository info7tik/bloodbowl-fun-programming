package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;
import fr.bloodbowl.testlib.DataBuilder;

public class MoveActionTest {
    private final Position player1Position = new Position(3, 4);
    private final Board boardWithPlayer = DataBuilder.boardWithPlayers(List.of(player1Position));
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
        Position destination = new Position(destRow, destColumn);
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
        Position distantPosition = new Position(destRow, destColumn);
        MoveAction action = new MoveAction(DataBuilder.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkPreconditionWhenMovingPlayersThatDoesNotExistMustFail() {
        MoveAction action = new MoveAction(DataBuilder.player1(), buildClosePosition());
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(DataBuilder.emptyBoard()));
    }

    @Test
    void checkPreconditionWhenMovingToOccupiedSquaresMustFail() {
        Player player1 = DataBuilder.player1();
        Position occupiedPosition = buildClosePosition();
        boardWithPlayer.placeAt(occupiedPosition, DataBuilder.getPlayer(2));
        MoveAction action = new MoveAction(player1, occupiedPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkStateForPlayersNotInHistoryMustFail() {
        MoveAction action = new MoveAction(DataBuilder.player1(), buildClosePosition());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }

    @Test
    void checkStateForPlayersWithEnoughMovementMustSucceed() throws FailedPreconditionException {
        MoveAction action = new MoveAction(DataBuilder.player1(), buildClosePosition());
        action.checkState(activePlayerHistory);
    }

    @Test
    void checkStateForPlayersWithExhaustedMovementMustFail() {
        Player player1 = DataBuilder.player1();
        MoveAction action = new MoveAction(player1, buildClosePosition());
        activePlayerHistory.registerMovement(player1.getIdentifier(), player1.getMovement());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }

    @Test
    void executeMoveActionMustMoveThePlayer() {
        Player player1 = DataBuilder.player1();
        Position destination = buildClosePosition();
        MoveAction action = new MoveAction(player1, destination);
        assertNotEquals(destination, boardWithPlayer.get(player1.getIdentifier()));
        action.execute(boardWithPlayer);
        assertEquals(destination, boardWithPlayer.get(player1.getIdentifier()));
    }

    private Position buildClosePosition() {
        return new Position(player1Position.getRow(), player1Position.getColumn() + 1);
    }
}

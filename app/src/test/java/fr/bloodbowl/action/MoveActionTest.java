package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.mock.DataBuilder;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;

public class MoveActionTest {
    private final Player player1 = DataBuilder.player1();
    private final Position player1Position = new Position(3, 4);
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
        Board boardWithPlayer = buildBoardWithPlayer(player1, player1Position);
        Position destination = new Position(destRow, destColumn);
        MoveAction action = new MoveAction(player1, destination);
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
        Board boardWithPlayer = buildBoardWithPlayer(player1, player1Position);
        Position distantPosition = new Position(destRow, destColumn);
        MoveAction action = new MoveAction(player1, distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkPreconditionWhenMovingPlayersThatDoesNotExistMustFail() {
        MoveAction action = new MoveAction(DataBuilder.player1(), buildClosePosition());
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(DataBuilder.emptyBoard()));
    }

    @Test
    void checkPreconditionWhenMovingToOccupiedSquaresMustFail() {
        Position player2Position = buildClosePosition();
        Board boardWithPlayer = buildBoardWithPlayer(player1, player1Position);
        boardWithPlayer.placeAt(player2Position, DataBuilder.player2());
        MoveAction action = new MoveAction(player1, player2Position);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(boardWithPlayer));
    }

    @Test
    void checkStateForPlayersNotInHistoryMustFail() {
        MoveAction action = new MoveAction(DataBuilder.player1(), buildClosePosition());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }

    @Test
    void checkStateForInactivePlayersMustFail() {
        Player player1 = new PlayerBuilder().withIdentifier("player1").withMovement(1).build();
        MoveAction action = new MoveAction(player1, buildClosePosition());
        activePlayerHistory.disablePlayer(player1.getIdentifier());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(activePlayerHistory));
    }

    @Test
    void checkStateForPlayersWithEnoughMovementMustSucceed() throws FailedPreconditionException {
        Player player1 = new PlayerBuilder().withIdentifier("player1").withMovement(1).build();
        MoveAction action = new MoveAction(player1, buildClosePosition());
        action.checkState(activePlayerHistory);
    }

    @Test
    void checkStateForPlayersWithExhaustedMovementMustFail() {
        MoveAction action = new MoveAction(player1, buildClosePosition());
        activePlayerHistory.registerMovement(player1.getIdentifier(), player1.getMovement());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(emptyHistory));
    }

    @Test
    void executeMoveActionMustMoveThePlayer() {
        Board boardWithPlayer = buildBoardWithPlayer(player1, player1Position);
        Position destination = buildClosePosition();
        MoveAction action = new MoveAction(player1, destination);
        assertNotEquals(destination, boardWithPlayer.get(player1.getIdentifier()));
        action.execute(boardWithPlayer, activePlayerHistory);
        assertEquals(destination, boardWithPlayer.get(player1.getIdentifier()));
        assertEquals(1, activePlayerHistory.getMovement(player1.getIdentifier()));
    }

    private Board buildBoardWithPlayer(Player player, Position position) {
        Board board = DataBuilder.emptyBoard();
        board.placeAt(position, player);
        return board;
    }

    private Position buildClosePosition() {
        return new Position(player1Position.getRow(), player1Position.getColumn() + 1);
    }
}

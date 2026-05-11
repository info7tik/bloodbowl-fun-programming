package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.testlib.DataGenerator;

public class MoveActionTest {
    private final Coordinate player1Position = new Coordinate(3, 4);
    private final Board board = DataGenerator.boardWithPlayer1(player1Position);

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentVerticalSquare() throws FailedPreconditionException {
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow(), player1Position.getColumn() + 1);
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow(), player1Position.getColumn() - 1);
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentHorizontalSquare() throws FailedPreconditionException {
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn());
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() - 1, player1Position.getColumn());
    }

    @Test
    void checkPreconditionWhenMovingPlayersToAdjacentDiagonalSquare() throws FailedPreconditionException {
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn() + 1);
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() + 1, player1Position.getColumn() - 1);
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() - 1, player1Position.getColumn() + 1);
        successfullyCheckPreconditionWhenMovingTo(player1Position.getRow() - 1, player1Position.getColumn() - 1);
    }

    private void successfullyCheckPreconditionWhenMovingTo(int destRow, int destColumn)
            throws FailedPreconditionException {
        Coordinate destination = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataGenerator.player1(), destination);
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
        MoveAction action = new MoveAction(DataGenerator.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(new Board()));
    }

    private void checkPreconditionWithErrorWhenMovingTo(int destRow, int destColumn) {
        Coordinate distantPosition = new Coordinate(destRow, destColumn);
        MoveAction action = new MoveAction(DataGenerator.player1(), distantPosition);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }
}

package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;
import fr.bloodbowl.testlib.DataBuilder;

public class FightActionTest {
    private final Player player1 = DataBuilder.player1();
    private Position player1Position = new Position(3, 4);
    private final Player player2 = DataBuilder.player2();
    private Position adjacentPosition = new Position(3, 5);
    private Position tooFarPosition = new Position(3, 6);
    private final TurnHistory activePlayerHistory = DataBuilder.historyWithActivePlayer1();

    @Test
    void checkPreconditionWhenFightingOpponentAtAdjacentPositionMustSucceed() throws FailedPreconditionException {
        Board board = buildBoardWithPlayer1();
        board.placeAt(adjacentPosition, player2);
        FightAction action = new FightAction(player1, player2);
        action.checkPrecondition(board);
    }

    @Test
    void checkPreconditionWhenFightingOpponentAtTooFarPositionMustFail() throws FailedPreconditionException {
        Board board = buildBoardWithPlayer1();
        board.placeAt(tooFarPosition, player2);
        FightAction action = new FightAction(player1, player2);
        assertThrows(FailedPreconditionException.class, () -> action.checkPrecondition(board));
    }

    @Test
    void checkStateForInactivePlayersMustFail() {
        FightAction action = new FightAction(player1, player2);
        activePlayerHistory.disablePlayer(player1.getIdentifier());
        assertThrows(FailedPreconditionException.class, () -> action.checkState(activePlayerHistory));
    }

    @Test
    void executeFightActionMustReturnOneRollDice() {
        FightAction action = new FightAction(player1, player2);
        DieRoll rolls = action.prepareDices();
        assertEquals(6, rolls.getCubeNumbers());
        assertEquals(1, rolls.getDiceNumber());
    }

    private Board buildBoardWithPlayer1() {
        Board board = DataBuilder.emptyBoard();
        board.placeAt(player1Position, player1);
        return board;
    }
}

package fr.bloodbowl.action;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.testlib.DataBuilder;

public class FightActionTest {
    private Player player1 = DataBuilder.player1();
    private Position player1Position = new Position(3, 4);
    private Player player2 = DataBuilder.player2();
    private Position adjacentPosition = new Position(3, 5);
    private Position tooFarPosition = new Position(3, 6);

    @Test
    void checkPreconditionWhenFightingOpponentAtAdjacentPositionMustSucceed() throws FailedPreconditionException {
        Board board = buildBoardWithPlayer();
        board.placeAt(adjacentPosition, player2);
        FightAction action = new FightAction(player1, player2);
        action.checkPrecondition(board);
    }

    @Test
    void checkPreconditionWhenFightingOpponentAtTooFarPositionMustFail() throws FailedPreconditionException {
        Board board = buildBoardWithPlayer();
        board.placeAt(tooFarPosition, player2);
        FightAction action = new FightAction(player1, player2);
        action.checkPrecondition(board);
    }

    private Board buildBoardWithPlayer() {
        Board board = DataBuilder.emptyBoard();
        board.placeAt(player1Position, player1);
        return board;
    }
}

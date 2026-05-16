package fr.bloodbowl.action;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.testlib.DataBuilder;

public class FightActionTest {
    private Position player1Position = new Position(3, 4);
    private Position adjacentPosition = new Position(3, 5);
    private Position tooFarPosition = new Position(3, 6);
    private Board board = DataBuilder.boardWithPlayers(List.of(player1Position, adjacentPosition, tooFarPosition));
    private Player player1 = DataBuilder.getPlayer(1);
    private Player player2 = DataBuilder.getPlayer(2);

    @Test
    void checkPreconditionWhenFightingOpponentAtAdjacentPositionMustSucceed() throws FailedPreconditionException {
        FightAction action = new FightAction(player1, player2);
        action.checkPrecondition(board);
    }
}

package fr.bloodbowl.action;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FightAction implements Action {
    @Getter
    private final Player player;
    @Getter
    private final Player opponent;

    @Override
    public void checkPrecondition(Board board) throws FailedPreconditionException {
        Position playerPosition = board.get(player.getIdentifier());
        Position opponentPosition = board.get(opponent.getIdentifier());
        if (!playerPosition.isAdjacent(opponentPosition)) {
            throw new FailedPreconditionException("opponent " + opponent + " is to far from player " + player);
        }
    }

    @Override
    public void checkState(TurnHistory history) throws FailedPreconditionException {
        if (!history.isActive(player.getIdentifier())) {
            throw new FailedPreconditionException("can not fight: " + player + " is not active");
        }
    }

    @Override
    public DieRoll prepareDices() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkDices(DieRollResult dices) throws FailedPreconditionException {
        // TODO Auto-generated method stub
    }

    @Override
    public void execute(Board board, TurnHistory history) {
    }
}

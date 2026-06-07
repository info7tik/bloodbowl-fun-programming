package fr.bloodbowl.action;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollFactory;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MoveAction implements Action {
    @Getter
    private final Player player;
    @Getter
    private final Position destination;

    @Override
    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (!board.has(player.getIdentifier())) {
            throw new FailedPreconditionException("can not move player " + player + ": player does not exist");
        }
        if (board.isOccupied(destination)) {
            throw new FailedPreconditionException(
                    "can not move player " + player + ": " + destination + " is occupied");
        }
        Position playerPosition = board.get(player.getIdentifier());
        if (playerPosition.equals(destination)) {
            throw new FailedPreconditionException(
                    "can not move player " + player + " at the same position " + playerPosition);
        }
        if (!playerPosition.isAdjacent(destination)) {
            throw new FailedPreconditionException(
                    "can not move player " + player + " from " + playerPosition + " to " + destination
                            + ": destination is too far away");
        }
    }

    @Override
    public void checkState(TurnHistory history) throws FailedPreconditionException {
        if (!history.isActive(player.getIdentifier())) {
            throw new FailedPreconditionException("can not move player " + player + ": inactive player");
        }
        if (history.getMovement(player.getIdentifier()) >= player.getMovement()) {
            throw new FailedPreconditionException("can not move player " + player + ": no remaining movement");
        }
    }

    @Override
    public DieRoll prepareDices() {
        return DieRollFactory.noRoll();
    }

    @Override
    public void checkDices(DieRollResult dices) throws FailedPreconditionException {
        // Nothing to check
    }

    @Override
    public void execute(Board board, TurnHistory history) {
        board.remove(player.getIdentifier());
        board.placeAt(destination, player);
        history.registerMovement(player.getIdentifier());
    }
}

package fr.bloodbowl.action;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.TurnHistory;

public class MoveAction implements Action {
    private final Player player;
    private final Coordinate destination;

    public MoveAction(Player player, Coordinate destination) {
        this.player = player;
        this.destination = destination;
    }

    @Override
    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (!board.has(player.getIdentifier())) {
            throw new FailedPreconditionException("can not move non existing player " + player);
        }
        Coordinate playerPosition = board.get(player.getIdentifier());
        int rowDifference = Math.abs(playerPosition.getRow() - destination.getRow());
        int columnDifference = Math.abs(playerPosition.getColumn() - destination.getColumn());
        if (rowDifference > 1 || columnDifference > 1) {
            throw new FailedPreconditionException(
                    "can not move player " + player + " from " + playerPosition + " to " + destination);
        }
        if (rowDifference + columnDifference == 0) {
            throw new FailedPreconditionException(
                    "can not move player " + player + " at the same position " + playerPosition);
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
    public void execute(Board board) {
    }
}

package fr.bloodbowl.action;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PlaceAction implements Action {
    @Getter
    private Player player;
    @Getter
    private Position position;

    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (board.has(player.getIdentifier())) {
            throw new FailedPreconditionException("can not place " + player + ": element is already on the board");
        }
        if (board.isOccupied(position)) {
            throw new FailedPreconditionException("can not place " + player + ": square is occupied");
        }
    }

    @Override
    public void checkState(TurnHistory history) throws FailedPreconditionException {
    }

    public void execute(Board board) {
        board.placeAt(position, player);
    }

    @Override
    public String toString() {
        return "<Place " + player + " at " + position + ">";
    }
}

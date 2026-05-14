package fr.bloodbowl.action;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.TurnHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PlaceAction implements Action {
    @Getter
    private Player player;
    @Getter
    private Coordinate coordinate;

    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (board.has(player)) {
            throw new FailedPreconditionException("can not place " + player + ": element is already on the board");
        }
        if (board.isOccupied(coordinate)) {
            throw new FailedPreconditionException("can not place " + player + ": square is occupied");
        }
    }

    @Override
    public void checkState(TurnHistory history) throws FailedPreconditionException {
    }

    public void execute(Board board) {
        board.placeAt(coordinate, player);
    }

    @Override
    public String toString() {
        return "<Place " + player + " at " + coordinate + ">";
    }
}

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
public class PlaceAction implements Action {
    @Getter
    private final Player player;
    @Getter
    private final Position position;

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

    @Override
    public DieRoll prepareDices() {
        return DieRollFactory.noRoll();
    }

    public void apply(DieRollResult dieResult, Board board, TurnHistory history) {
        board.placeAt(position, player);
    }

    @Override
    public String toString() {
        return "<Place " + player + " at " + position + ">";
    }
}

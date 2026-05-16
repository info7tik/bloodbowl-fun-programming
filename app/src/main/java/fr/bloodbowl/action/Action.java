package fr.bloodbowl.action;

import java.util.List;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;

public interface Action {
    void checkPrecondition(Board board) throws FailedPreconditionException;

    void checkState(TurnHistory history) throws FailedPreconditionException;

    List<DieRoll> prepareDices();

    void execute(Board board, TurnHistory history, DieRollResult roll);
}

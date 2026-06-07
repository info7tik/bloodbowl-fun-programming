package fr.bloodbowl.action;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;

public interface Action {
    void checkPrecondition(Board board) throws FailedPreconditionException;

    void checkState(TurnHistory history) throws FailedPreconditionException;

    DieRoll prepareDices();

    void checkDices(DieRollResult dices) throws FailedPreconditionException;

    void apply(Board board, TurnHistory history);
}

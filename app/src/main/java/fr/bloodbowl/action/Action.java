package fr.bloodbowl.action;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;

public interface Action {
    void checkPrecondition(Board board) throws FailedPreconditionException;

    void checkState(TurnHistory history) throws FailedPreconditionException;

    void execute(Board board);
}

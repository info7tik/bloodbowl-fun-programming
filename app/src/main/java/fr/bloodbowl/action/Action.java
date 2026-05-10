package fr.bloodbowl.action;

import fr.bloodbowl.models.Board;

public interface Action {
    void checkPrecondition(Board board) throws FailedPreconditionException;

    void execute(Board board);
}

package fr.bloodbowl.mock;

import fr.bloodbowl.action.Action;
import fr.bloodbowl.action.FailedPreconditionException;
import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollFactory;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;

public class MockAction implements Action {
    private final boolean preconditionFailure;
    private final boolean checkStateFailure;
    private final boolean checkDicesFailure;
    private boolean executed = false;

    private MockAction(boolean withPreconditionFailure, boolean withCheckStateFailure, boolean withCheckDicesFailure) {
        this.preconditionFailure = withPreconditionFailure;
        this.checkStateFailure = withCheckStateFailure;
        this.checkDicesFailure = withCheckDicesFailure;
    }

    public static MockAction successfulAction() {
        return new MockAction(false, false, false);
    }

    public static MockAction actionWithPreconditionFailure() {
        return new MockAction(true, false, false);
    }

    public static MockAction actionWithStateCheckFailure() {
        return new MockAction(false, true, false);
    }

    public static MockAction actionWithDiceCheckFailure() {
        return new MockAction(false, false, true);
    }

    @Override
    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (preconditionFailure) {
            throw new FailedPreconditionException("for testing purpose");
        }
    }

    @Override
    public void checkState(TurnHistory history) throws FailedPreconditionException {
        if (checkStateFailure) {
            throw new FailedPreconditionException("for testing purpose");
        }
    }

    @Override
    public void checkDices(DieRollResult dices) throws FailedPreconditionException {
        if (checkDicesFailure) {
            throw new FailedPreconditionException("for testing purpose");
        }
    }

    @Override
    public DieRoll prepareDices() {
        return DieRollFactory.noRoll();
    }

    @Override
    public void apply(Board board, TurnHistory history) {
        this.executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

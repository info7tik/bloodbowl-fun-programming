package fr.bloodbowl.testlib.mock;

import fr.bloodbowl.action.Action;
import fr.bloodbowl.action.FailedPreconditionException;
import fr.bloodbowl.models.Board;

public class MockAction implements Action {
    private final boolean shouldFail;
    private boolean executed = false;

    private MockAction(boolean shouldFail) {
        this.shouldFail = shouldFail;
    }

    public static MockAction successfulAction() {
        return new MockAction(false);
    }

    public static MockAction failedAction() {
        return new MockAction(true);
    }

    @Override
    public void checkPrecondition(Board board) throws FailedPreconditionException {
        if (shouldFail) {
            throw new FailedPreconditionException("for testing purpose");
        }
    }

    @Override
    public void execute(Board board) {
        this.executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

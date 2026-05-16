package fr.bloodbowl.testlib.mock;

import java.util.ArrayList;
import java.util.List;

import fr.bloodbowl.action.Action;
import fr.bloodbowl.action.FailedPreconditionException;
import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;

public class MockAction implements Action {
    private final boolean shouldFail;
    private boolean executed = false;
    private List<DieRoll> rolls = new ArrayList<>();

    private MockAction(boolean shouldFail) {
        this.shouldFail = shouldFail;
    }

    public void addDieRoll(DieRoll roll) {
        rolls.add(roll);
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
    public void checkState(TurnHistory history) throws FailedPreconditionException {
    }

    @Override
    public List<DieRoll> prepareDices() {
        return new ArrayList<>(rolls);
    }

    @Override
    public void execute(Board board, TurnHistory history, DieRollResult roll) {
        this.executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

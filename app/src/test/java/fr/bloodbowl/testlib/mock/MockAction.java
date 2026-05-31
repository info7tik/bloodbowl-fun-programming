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
    private final boolean preconditionFailure;
    private final boolean checkStateFailure;
    private boolean executed = false;
    private List<DieRoll> rolls = new ArrayList<>();

    private MockAction(boolean withPreconditionFailure, boolean withCheckStateFailure) {
        this.preconditionFailure = withPreconditionFailure;
        this.checkStateFailure = withCheckStateFailure;
    }

    public void addDieRoll(DieRoll roll) {
        rolls.add(roll);
    }

    public static MockAction successfulAction() {
        return new MockAction(false, false);
    }

    public static MockAction actionWithPreconditionFailure() {
        return new MockAction(true, false);
    }

    public static MockAction actionWithStateCheckFailure() {
        return new MockAction(false, true);
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

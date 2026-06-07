package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.mock.MockAction;

public class ActionExecutorTest {
    private ActionExecutor executor = new ActionExecutor();

    @Test
    void execute() {
        MockAction action = MockAction.successfulAction();
        executor.execute(action);
        assertTrue(action.isExecuted());
    }

    @Test
    void executeMustFailWhileTestingPreconditions() {
        MockAction action = MockAction.actionWithPreconditionFailure();
        executor.execute(action);
        assertFalse(action.isExecuted());
    }

    @Test
    void executeMustFailWhileCheckingState() {
        MockAction action = MockAction.actionWithStateCheckFailure();
        executor.execute(action);
        assertFalse(action.isExecuted());
    }

    @Test
    void executeWithDieRolls() {
        throw new UnsupportedOperationException();
    }
}

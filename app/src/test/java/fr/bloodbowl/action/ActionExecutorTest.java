package fr.bloodbowl.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.dices.DieComparator;
import fr.bloodbowl.dices.DieRollFactory;
import fr.bloodbowl.testlib.mock.MockAction;

public class ActionExecutorTest {
    private ActionExecutor executor = new ActionExecutor();

    @Test
    void execute() {
        MockAction action = MockAction.successfulAction();
        executor.execute(action);
        assertTrue(action.isExecuted());
    }

    @Test
    void executeFailedAction() {
        MockAction action = MockAction.failedAction();
        executor.execute(action);
        assertFalse(action.isExecuted());
    }

    @Test
    void executeWithDieRolls() {
        MockAction action = MockAction.successfulAction();
        action.addDieRoll(DieRollFactory.sixCubesRoll(DieComparator.EQUAL_OR_GREATER_THAN, 8));
        executor.execute(action);
        assertFalse(action.isExecuted());
    }
}

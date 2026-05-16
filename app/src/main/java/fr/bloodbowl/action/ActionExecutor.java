package fr.bloodbowl.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.TurnHistory;
import lombok.Getter;

public class ActionExecutor {
    private Logger logger = LoggerFactory.getLogger(ActionExecutor.class);
    private final DiceRoller roller;
    @Getter
    private final Board board = new Board();
    private final TurnHistory history = new TurnHistory();

    public ActionExecutor() {
        this.roller = new DiceRoller();
    }

    public ActionExecutor(DiceRoller roller) {
        this.roller = roller;
    }

    public void execute(Action action) {
        try {
            logger.info("executing " + action);
            action.checkPrecondition(board);
            List<DieRoll> rolls = action.prepareDices();
            DieRollResult result = roller.execute(rolls);
            action.execute(board, history, result);
        } catch (FailedPreconditionException ex) {
            logger.error("action " + action.getClass().getSimpleName() + "fails: ", ex);
        }
    }
}

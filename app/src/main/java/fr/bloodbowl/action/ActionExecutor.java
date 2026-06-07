package fr.bloodbowl.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bloodbowl.dices.DieRoll;
import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.dices.RandomIntegerGenerator;
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
        this.roller = new DiceRoller(new RandomIntegerGenerator());
    }

    public ActionExecutor(DiceRoller roller) {
        this.roller = roller;
    }

    public void execute(Action action) {
        try {
            logger.info("checking preconditions for " + action);
            action.checkPrecondition(board);
            logger.info("checking history state for " + action);
            action.checkState(history);
            logger.info("rolling the dices for " + action);
            DieRoll roll = action.prepareDices();
            logger.info("roll the dices " + roll + " for " + action);
            DieRollResult result = roller.execute(roll);
            logger.info("register the dice result " + result + " for " + action);
            action.checkDices(result);
            logger.info("apply the action " + action);
            action.apply(board, history);
        } catch (FailedPreconditionException ex) {
            logger.error("action " + action.getClass().getSimpleName() + "fails: ", ex);
        }
    }
}

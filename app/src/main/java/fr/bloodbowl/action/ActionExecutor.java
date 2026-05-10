package fr.bloodbowl.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bloodbowl.models.Board;
import lombok.Getter;

public class ActionExecutor {
    private Logger logger = LoggerFactory.getLogger(ActionExecutor.class);
    @Getter
    private Board board = new Board();

    public void execute(Action action) {
        try {
            logger.info("executing " + action);
            action.checkPrecondition(board);
            action.execute(board);
        } catch (FailedPreconditionException ex) {
            logger.error("action " + action.getClass().getSimpleName() + "fails: ", ex);
        }
    }
}

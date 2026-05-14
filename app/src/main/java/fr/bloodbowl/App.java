package fr.bloodbowl;

import fr.bloodbowl.action.ActionExecutor;
import fr.bloodbowl.action.MoveAction;
import fr.bloodbowl.action.PlaceAction;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.Position;

public class App {

    public static void main(String[] args) {
        Player orc1 = new PlayerBuilder().withIdentifier("orc1").withMovement(4).build();
        Player human1 = new PlayerBuilder().withIdentifier("human1").withMovement(6).build();

        ActionExecutor executor = new ActionExecutor();
        executor.execute(new PlaceAction(orc1, new Position(2, 4)));
        executor.execute(new PlaceAction(human1, new Position(4, 4)));
        executor.execute(new MoveAction(orc1, new Position(3, 4)));
    }
}

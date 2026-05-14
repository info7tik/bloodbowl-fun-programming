package fr.bloodbowl;

import fr.bloodbowl.action.ActionExecutor;
import fr.bloodbowl.action.PlaceAction;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;

public class App {

    public static void main(String[] args) {
        Player orc1 = new Player("orc1", 4);
        Player human1 = new Player("human1", 6);

        ActionExecutor executor = new ActionExecutor();
        executor.execute(new PlaceAction(orc1, new Coordinate(2, 4)));
        executor.execute(new PlaceAction(human1, new Coordinate(4, 4)));
    }
}

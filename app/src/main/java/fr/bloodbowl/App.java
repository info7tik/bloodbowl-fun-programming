package fr.bloodbowl;

import fr.bloodbowl.action.ActionExecutor;
import fr.bloodbowl.action.ActionFactory;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.Position;

public class App {

    public static void main(String[] args) {
        Player orc1 = new PlayerBuilder().withIdentifier("orc1").withMovement(4).build();
        Player human1 = new PlayerBuilder().withIdentifier("human1").withMovement(6).build();

        ActionExecutor executor = new ActionExecutor();
        executor.execute(ActionFactory.placeAt(orc1, new Position(2, 4)));
        executor.execute(ActionFactory.placeAt(human1, new Position(4, 4)));
        executor.execute(ActionFactory.move(orc1, new Position(3, 4)));
    }
}

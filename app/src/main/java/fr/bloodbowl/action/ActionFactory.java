package fr.bloodbowl.action;

import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.Position;

public class ActionFactory {
    public static PlaceAction placeAt(Player player, Position position) {
        return new PlaceAction(player, position);
    }

    public static MoveAction move(Player player, Position destination) {
        return new MoveAction(player, destination);
    }

    public static FightAction fight(Player player, Player opponent) {
        return new FightAction(player, opponent);
    }
}

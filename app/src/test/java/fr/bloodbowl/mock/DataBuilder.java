package fr.bloodbowl.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.bloodbowl.dices.DieRollResult;
import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.TurnHistory;

public class DataBuilder {
    private static Map<String, Player> players = new HashMap<>();

    public static Player player1() {
        return getOrBuildPlayer("player1");
    }

    public static Player player2() {
        return getOrBuildPlayer("player2");
    }

    public static Player getOrBuildPlayer(String playerId) {
        Player player = players.get(playerId);
        if (player != null) {
            return player;
        }
        return new PlayerBuilder().withIdentifier(playerId).build();
    }

    public static Board emptyBoard() {
        return new Board();
    }

    public static TurnHistory emptyHistory() {
        return new TurnHistory();
    }

    public static TurnHistory historyWithActivePlayer1() {
        TurnHistory history = emptyHistory();
        history.addActivePlayer(player1().getIdentifier());
        return history;
    }

    public static DieRollResult emptyResult() {
        return new DieRollResult(List.of(), false);
    }
}

package fr.bloodbowl.testlib;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;

public class DataBuilder {
    private static List<Player> players = new ArrayList<>();

    public static List<Player> buildPlayers(int numberOfPlayers, int movement) {
        IntStream.of(numberOfPlayers).forEach(nb -> {
            Player p = new PlayerBuilder().withIdentifier("player1").withMovement(movement).build();
            players.add(p);
        });
        return players;
    }

    public static List<Player> buildPlayers(int numberOfPlayers) {
        IntStream.of(numberOfPlayers).forEach(nb -> {
            Player p = new PlayerBuilder().withIdentifier("player" + (nb + 1)).withMovement(1).build();
            players.add(p);
        });
        return players;
    }

    public static Player player1() {
        if (players.isEmpty()) {
            buildPlayers(1);
        }
        return getPlayer(1);
    }

    public static Player getPlayer(int playerNumber) {
        int playerIndex = playerNumber - 1;
        if (playerIndex < players.size() && playerIndex > 0) {
            return players.get(0);
        }
        throw new IllegalArgumentException("player" + playerNumber + " does not exist");
    }

    public static Board emptyBoard() {
        return new Board();
    }

    public static Board boardWithPlayers(List<Position> playerPositions) {
        Board board = emptyBoard();
        if (!players.isEmpty()) {
            throw new IllegalArgumentException(
                    "can not build the board: the board must be empty. Too many players: " + players.size()
                            + " players");
        }
        buildPlayers(playerPositions.size());
        for (int index = 0; index < players.size(); index++) {
            board.placeAt(playerPositions.get(index), players.get(index));
        }
        return board;
    }

    public static TurnHistory emptyHistory() {
        return new TurnHistory();
    }

    public static TurnHistory historyWithActivePlayer1() {
        TurnHistory history = emptyHistory();
        history.addActivePlayer(player1().getIdentifier());
        return history;
    }
}

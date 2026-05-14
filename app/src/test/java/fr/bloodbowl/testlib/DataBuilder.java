package fr.bloodbowl.testlib;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.PlayerBuilder;
import fr.bloodbowl.models.Position;
import fr.bloodbowl.models.TurnHistory;

public class DataBuilder {
    public static Player player1() {
        return new PlayerBuilder().withIdentifier("player1").withMovement(1).build();
    }

    public static Player player2() {
        return new PlayerBuilder().withIdentifier("player2").withMovement(2).build();
    }

    public static Board emptyBoard() {
        return new Board();
    }

    public static Board boardWithPlayer1(Position playerPosition) {
        Board board = emptyBoard();
        board.placeAt(playerPosition, player1());
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

package fr.bloodbowl.testlib;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.TurnHistory;

public class DataBuilder {
    public static Player player1() {
        int movement = 1;
        return new Player("player1", movement);
    }

    public static Player player2() {
        int movement = 2;
        return new Player("player2", movement);
    }

    public static Board emptyBoard() {
        return new Board();
    }

    public static Board boardWithPlayer1(Coordinate playerPosition) {
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

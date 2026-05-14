package fr.bloodbowl.testlib;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;
import fr.bloodbowl.models.TurnHistory;

public class DataGenerator {
    public static Player player1() {
        return new Player("player1");
    }

    public static Player player2() {
        return new Player("player2");
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
}

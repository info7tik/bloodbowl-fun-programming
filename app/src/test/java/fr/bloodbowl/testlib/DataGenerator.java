package fr.bloodbowl.testlib;

import fr.bloodbowl.models.Board;
import fr.bloodbowl.models.Coordinate;
import fr.bloodbowl.models.Player;

public class DataGenerator {
    public static Player player1() {
        return new Player("player1");
    }

    public static Player player2() {
        return new Player("player2");
    }

    public static Board boardWithPlayer1(Coordinate playerPosition) {
        Board board = new Board();
        board.placeAt(playerPosition, player1());
        return board;
    }
}

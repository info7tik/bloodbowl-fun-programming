package fr.bloodbowl.testlib;

import fr.bloodbowl.models.Player;

public class DataGenerator {
    public static Player player1() {
        return new Player("player1");
    }

    public static Player player2() {
        return new Player("player2");
    }
}

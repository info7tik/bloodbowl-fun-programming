package fr.bloodbowl.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.testlib.DataBuilder;

public class TurnHistoryTest {
    private TurnHistory emptyHistory = DataBuilder.emptyHistory();
    private TurnHistory activePlayerHistory = DataBuilder.historyWithActivePlayer1();
    private Player player1 = DataBuilder.player1();
    private Player player2 = DataBuilder.player2();

    @Test
    void isActivePlayerWithSuccess() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        assertTrue(emptyHistory.isActive(player1.getIdentifier()));
    }

    @Test
    void isActivePlayerWithError() {
        assertFalse(emptyHistory.isActive(player1.getIdentifier()));
    }

    @Test
    void addActivePlayerWithSuccess() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        assertTrue(emptyHistory.isActive(player1.getIdentifier()));
    }

    @Test
    void getMovementOfNotExistingPlayerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.getMovement("not_existing"));
    }

    @Test
    void registerPlayerMovementOfNotExistingPlayerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.registerMovement("not_existing"));
    }

    @Test
    void registerPlayerMovementWithSuccess() {
        int oneSquareMove = 1;
        activePlayerHistory.registerMovement(player1.getIdentifier());
        assertEquals(oneSquareMove, activePlayerHistory.getMovement(player1.getIdentifier()));
    }

    @Test
    void registerPlayerMovementOfMoreThanOneSquareWithSuccess() {
        int multipleSquareMove = 3;
        activePlayerHistory.registerMovement(player1.getIdentifier(), multipleSquareMove);
        assertEquals(multipleSquareMove, activePlayerHistory.getMovement(player1.getIdentifier()));
    }

    @Test
    void registerMUltiplePlayerMovementsWithSuccess() {
        activePlayerHistory.addActivePlayer(player2.getIdentifier());
        int oneSquareMove = 1;
        activePlayerHistory.registerMovement(player1.getIdentifier());
        activePlayerHistory.registerMovement(player1.getIdentifier());
        activePlayerHistory.registerMovement(player2.getIdentifier());
        assertEquals(oneSquareMove * 2, activePlayerHistory.getMovement(player1.getIdentifier()));
        assertEquals(oneSquareMove, activePlayerHistory.getMovement(player2.getIdentifier()));
    }
}

package fr.bloodbowl.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.bloodbowl.mock.DataBuilder;

public class TurnHistoryTest {
    private TurnHistory emptyHistory = DataBuilder.emptyHistory();
    private TurnHistory activePlayerHistory = DataBuilder.historyWithActivePlayer1();
    private final Player player1 = DataBuilder.player1();
    private final Player player2 = DataBuilder.player2();

    @Test
    void addActivePlayerWithSuccess() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        assertTrue(emptyHistory.isActive(player1.getIdentifier()));
    }

    @Test
    void isActivePlayerWithNotExistingPlayerReturnFalse() {
        assertFalse(emptyHistory.isActive("notExistingPlayer"));
    }

    @Test
    void getMovementOfNotExistingPlayerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.getMovement("notExisting"));
    }

    @Test
    void registerPlayerMovementOfNotExistingPlayerThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.registerMovement("notExisting"));
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
    void registerMultiplePlayerMovementsWithSuccess() {
        activePlayerHistory.addActivePlayer(player2.getIdentifier());
        int oneSquareMove = 1;
        activePlayerHistory.registerMovement(player1.getIdentifier());
        activePlayerHistory.registerMovement(player1.getIdentifier());
        activePlayerHistory.registerMovement(player2.getIdentifier());
        assertEquals(oneSquareMove * 2, activePlayerHistory.getMovement(player1.getIdentifier()));
        assertEquals(oneSquareMove, activePlayerHistory.getMovement(player2.getIdentifier()));
    }

    @Test
    void addActivePlayerAfterRegisteringMovementDoNotModifyTheMovementCounter() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        int movement = 2;
        assertEquals(0, activePlayerHistory.getMovement(player1.getIdentifier()));
        activePlayerHistory.registerMovement(player1.getIdentifier(), movement);
        assertEquals(movement, activePlayerHistory.getMovement(player1.getIdentifier()));
        emptyHistory.addActivePlayer(player1.getIdentifier());
        assertEquals(movement, activePlayerHistory.getMovement(player1.getIdentifier()));
    }

    @Test
    void disableActivePlayerMustSucceed() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        emptyHistory.disablePlayer(player1.getIdentifier());
        assertFalse(emptyHistory.isActive(player1.getIdentifier()));
    }

    @Test
    void disableActivePlayerMultipleTimesMustFail() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        emptyHistory.disablePlayer(player1.getIdentifier());
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.disablePlayer(player1.getIdentifier()));
    }

    @Test
    void disablePlayerThatDoesNotExistMustSucceed() {
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.disablePlayer("notExistingPlayer"));
    }

    @Test
    void activateDisabledPlayersMustFail() {
        emptyHistory.addActivePlayer(player1.getIdentifier());
        emptyHistory.disablePlayer(player1.getIdentifier());
        assertThrows(IllegalArgumentException.class, () -> emptyHistory.addActivePlayer(player1.getIdentifier()));
    }
}

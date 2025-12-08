package lastpencil.service;

import lastpencil.exception.ManyPencilsTakenException;
import lastpencil.pojo.Player;

import java.util.List;

/**
 * Core service that manages the state and rules of the game.
 * It handles turn switching, move application and game termination.
 */
public class LastPencilGameService {
    private final List<Player> PLAYERS_LIST;
    private Player currentPlayer;
    private int pencilsNum;

    /**
     * Creates the game service with the list of players.
     *
     * @param playersList the players participating in the game
     */
    public LastPencilGameService(List<Player> playersList) {
        this.PLAYERS_LIST = playersList;
    }

    public int getPencilsNum() {
        return pencilsNum;
    }

    public void setPencilsNum(int pencilsNum) {
        this.pencilsNum = pencilsNum;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Applies a move to the game state.
     *
     * @param pencilsToGet number of pencils taken by the player
     * @throws ManyPencilsTakenException if the player tries to take too many pencils
     */
    public void applyMove(int pencilsToGet) {
        if (pencilsToGet > pencilsNum) {
            throw new ManyPencilsTakenException();
        }
        pencilsNum -= pencilsToGet;
    }

    /**
     * Switches the current player to the next one.
     */
    public void switchTurn() {
        int currentIndex = PLAYERS_LIST.indexOf(currentPlayer);
        currentPlayer = PLAYERS_LIST.get((currentIndex + 1) % PLAYERS_LIST.size());
    }

    /**
     * @return true if the game has ended, false otherwise
     */
    public boolean isGameOver() {
        return pencilsNum <= 0;
    }
}

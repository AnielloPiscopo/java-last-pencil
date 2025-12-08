package lastpencil.service;

import lastpencil.exception.ManyPencilsTakenException;
import lastpencil.pojo.Player;

import java.util.List;

public class LastPencilGameService {
    private final List<Player> PLAYERS_LIST;
    private Player currentPlayer;
    private int pencilsNum;

    public LastPencilGameService(List<Player> playersList) {
        this.PLAYERS_LIST = playersList;
    }

    public int getPencilsNum() {
        return pencilsNum;
    }

    public void setPencilsNum(int pencilsNum){
        this.pencilsNum = pencilsNum;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void applyMove(int pencilsToGet) {
        if (pencilsToGet > pencilsNum) {
            throw new ManyPencilsTakenException();
        }
        pencilsNum -= pencilsToGet;
    }

    public void switchTurn() {
        int currentIndex = PLAYERS_LIST.indexOf(currentPlayer);
        currentPlayer = PLAYERS_LIST.get((currentIndex + 1) % PLAYERS_LIST.size());
    }

    public boolean isGameOver() {
        return pencilsNum <= 0;
    }
}

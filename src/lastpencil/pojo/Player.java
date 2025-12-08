package lastpencil.pojo;

import lastpencil.enums.PlayerType;

public class Player {
    private String name;
    private PlayerType playerType;

    public Player() {
    }

    public Player(String name) {
        this(name , PlayerType.fromString("human"));
    }

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
    }

    public Player(String name, String playerType) {
        this(name , PlayerType.fromString(playerType));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getPlayerTypeStr(){
        return getPlayerType().toString();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerType=" + playerType +
                '}';
    }
}

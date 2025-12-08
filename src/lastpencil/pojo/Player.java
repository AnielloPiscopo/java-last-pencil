package lastpencil.pojo;

import lastpencil.enums.PlayerType;

/**
 * Represents a player in the Last Pencil game.
 * A player has a name and a type (human or bot).
 */
public class Player {
    private String name;
    private PlayerType playerType;

    /** Default constructor required for frameworks or serialization. */
    public Player() {
    }

    /**
     * Creates a human player with the given name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this(name , PlayerType.fromString("human"));
    }

    /**
     * Creates a player with a specific type.
     *
     * @param name       the name of the player
     * @param playerType the type of the player
     */
    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
    }

    /**
     * Creates a player using a string representation of the type.
     *
     * @param name       the name of the player
     * @param playerType the type as a string
     */
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

    /**
     * @return the player type as a string
     */
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

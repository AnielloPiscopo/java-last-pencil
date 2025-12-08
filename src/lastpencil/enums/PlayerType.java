package lastpencil.enums;

/**
 * Represents the type of  player in the game.
 * A player can be either a human or a bot.
 */
public enum PlayerType {
    HUMAN,
    BOT;

    /**
     * Converts a string value into a PlayerType.
     *
     * @param value the string representation of the player type
     * @return the corresponding PlayerType enum value
     * @throws IllegalArgumentException if the value does not match any enum
     */
    public static PlayerType fromString(String value){
        return PlayerType.valueOf(value.trim().toUpperCase());
    }
}

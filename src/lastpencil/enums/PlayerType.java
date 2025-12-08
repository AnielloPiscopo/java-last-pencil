package lastpencil.enums;

public enum PlayerType {
    HUMAN,
    BOT;

    public static PlayerType fromString(String value){
        return PlayerType.valueOf(value.trim().toUpperCase());
    }
}

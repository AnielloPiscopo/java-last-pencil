package lastpencil.exception;

/**
 * Exception thrown when the game is started with
 * an insufficient number of players.
 */
public class NotEnoughPlayersException extends RuntimeException {
    /** Creates the exception with a default message. */
    public NotEnoughPlayersException() {
        super("Not enough player in game");
    }
}

package lastpencil.exception;

/**
 * Exception thrown when a player tries to take more pencils
 * than the number currently available in the game.
 */
public class ManyPencilsTakenException extends RuntimeException{
    /** Creates the exception with a default message. */
    public ManyPencilsTakenException() {
        super("Too many pencils were taken");
    }
}

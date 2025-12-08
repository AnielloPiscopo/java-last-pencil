package lastpencil.exception;

public class NotEnoughPlayersException extends RuntimeException {
    public NotEnoughPlayersException() {
        super("Not enough player in game");
    }
}

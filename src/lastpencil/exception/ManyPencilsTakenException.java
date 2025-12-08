package lastpencil.exception;

public class ManyPencilsTakenException extends RuntimeException{
    public ManyPencilsTakenException() {
        super("Too many pencils were taken");
    }
}

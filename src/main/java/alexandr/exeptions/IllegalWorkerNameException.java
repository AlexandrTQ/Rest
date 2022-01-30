package alexandr.exeptions;

public class IllegalWorkerNameException extends RuntimeException {
    public IllegalWorkerNameException(String message) {
        super(message);
    }
}

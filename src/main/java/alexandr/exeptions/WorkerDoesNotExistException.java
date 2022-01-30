package alexandr.exeptions;

public class WorkerDoesNotExistException extends RuntimeException {
    public WorkerDoesNotExistException(String message) {
        super(message);
    }
}

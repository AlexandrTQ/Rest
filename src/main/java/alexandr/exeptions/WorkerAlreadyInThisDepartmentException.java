package alexandr.exeptions;

public class WorkerAlreadyInThisDepartmentException extends RuntimeException {
    public WorkerAlreadyInThisDepartmentException(String message) {
        super(message);
    }
}

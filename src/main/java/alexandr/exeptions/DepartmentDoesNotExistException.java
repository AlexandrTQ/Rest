package alexandr.exeptions;

public class DepartmentDoesNotExistException extends RuntimeException {
    public DepartmentDoesNotExistException(String message) {
        super(message);
    }
}

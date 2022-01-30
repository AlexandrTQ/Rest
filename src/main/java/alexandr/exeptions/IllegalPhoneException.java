package alexandr.exeptions;

public class IllegalPhoneException extends RuntimeException {
    public IllegalPhoneException(String message) {
        super(message);
    }
}

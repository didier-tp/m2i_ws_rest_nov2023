package tp.web.exception;

public class MyConflictException extends RuntimeException {

	public MyConflictException() {
		super("conflict");
	}

	public MyConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyConflictException(String message) {
		super(message);
	}

}

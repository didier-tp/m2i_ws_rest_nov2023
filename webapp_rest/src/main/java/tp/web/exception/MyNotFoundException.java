package tp.web.exception;

public class MyNotFoundException extends RuntimeException {
	public MyNotFoundException() {
		super("not found");
	}

	public MyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyNotFoundException(String message) {
		super(message);
	}
}

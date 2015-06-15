package ua.pri.exceptions;

public class RegistrationException extends Exception {
	private static final long serialVersionUID = 11L;

	public RegistrationException(String message) {
		super(message);
	}

	public RegistrationException(Throwable cause) {
		super(cause);
	}

	public RegistrationException(String message, Throwable cause) {
		super(message, cause);
	}
}

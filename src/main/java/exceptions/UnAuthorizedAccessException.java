package exceptions;

public class UnAuthorizedAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UnAuthorizedAccessException(Long id, String firstName, String lastName) {
		super(String.format("UnAuthorized access from: %d, %s, %s", id, firstName, lastName));
	}

}

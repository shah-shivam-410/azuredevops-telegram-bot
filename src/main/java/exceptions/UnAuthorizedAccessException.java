package exceptions;

public class UnAuthorizedAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UnAuthorizedAccessException(Long id, String firstName, String lastName) {
		super(String.format("Unauthorized access from: [id: %d, firstname: %s, lastname: %s]", id, firstName, lastName));
	}
	
}

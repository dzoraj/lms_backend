package lmsprojekat.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6703832061417680603L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}

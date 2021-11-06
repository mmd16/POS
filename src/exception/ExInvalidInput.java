package exception;


public class ExInvalidInput extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExInvalidInput() {
        super("*** The input is invalid. ***");
    }
}
package exception;

public class ExZeroOrNegative extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExZeroOrNegative() {
        super("*** The number you inserted should not be zero or negative. ***");
    }
}

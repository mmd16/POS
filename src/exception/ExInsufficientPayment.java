package exception;

public class ExInsufficientPayment extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExInsufficientPayment() {
        super("*** Payment is insufficient, checkout Process aborted ***");
    }
}

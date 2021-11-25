package exception;

public class ExInvalidDate extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExInvalidDate() {
        super("*** Date is invalid! ***\n");
    }
}

package exception;

public class ExProductTypeNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExProductTypeNotExists() {
        super("*** Please input product type as Food or Equipment! ***");
    }
}

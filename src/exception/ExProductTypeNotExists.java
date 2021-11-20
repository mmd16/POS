package exception;

public class ExProductTypeNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExProductTypeNotExists() {
        super("*** Please input the correct product type! ***");
    }
}

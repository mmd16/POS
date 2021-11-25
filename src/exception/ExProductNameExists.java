package exception;

public class ExProductNameExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExProductNameExists() {
        super("*** Product Name Exists! ***\n");
    }
}

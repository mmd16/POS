package exception;

public class ExProductNameNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExProductNameNotExists() {
        super("*** Product Name not exists! ***\n");
    }
}

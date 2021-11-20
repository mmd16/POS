package exception;

public class ExNoSalesExists extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExNoSalesExists() {
        super("*** No Sales Exists! ***");
    }
}

package exception;

public class ExNoSalesInSelectedAge extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExNoSalesInSelectedAge() {
        super("*** No Sales in selected Age Group! ***\n");
    }
}

package exception;

public class ExCustomerNotFound extends Exception {	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExCustomerNotFound() {
        super("Customer name does not exist.");
    }

    public ExCustomerNotFound(String message) {
        super(message);
    }
}

package exception;

public class ExEmployeeIdNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExEmployeeIdNotExists() {
        super("*** Employee ID not exists! ***");
    }
}

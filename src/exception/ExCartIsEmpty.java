package exception;

public class ExCartIsEmpty extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExCartIsEmpty() {
        super("*** The Cart is Empty, Checkout Can't be proceed. ***");
    }
}

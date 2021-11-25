package exception;

public class ExFailInRefund extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExFailInRefund() {
        super("*** Refund Process is aborted, Please check that the input information is correct! ***\n");
    }
}

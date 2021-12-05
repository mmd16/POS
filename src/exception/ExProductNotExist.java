package exception;

public class ExProductNotExist extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExProductNotExist() {
        super("*** Product Not Exist! ***\n");
    }
}

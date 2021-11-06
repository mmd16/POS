package exception;

public class ExMemberIdNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExMemberIdNotExists() {
        super("*** Member ID not exists! ***");
    }
}

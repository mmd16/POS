package exception;

public class ExWrongCommand extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExWrongCommand() {
        super("");
    }

    public ExWrongCommand(String message) {
        super(message);
    }
}

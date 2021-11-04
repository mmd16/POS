package exception;

public class ExZeroOrNegative extends Exception {
	public ExZeroOrNegative() {
        super("*** The number you inserted should not be zero or negative. ***");
    }
}

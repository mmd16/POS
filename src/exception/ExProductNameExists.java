package exception;

public class ExProductNameExists extends Exception {
	public ExProductNameExists() {
        super("*** Product Name Exists! ***");
    }
}

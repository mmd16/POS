package exception;

public class ExProductTypeNotExists extends Exception {
	public ExProductTypeNotExists() {
        super("*** Please input product type as Food or Equipment! ***");
    }
}

package exception;

public class ExProductNotFound extends Exception {
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExProductNotFound() {
	        super("Product name does not exist.");
	    }

	    public ExProductNotFound(String message) {
	        super(message);
	    }
}

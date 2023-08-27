package j2ee.lab03;

public class ItemNotFound extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFound(String message) {
        super(message);
    }
}

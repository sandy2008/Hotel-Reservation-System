/**
 * Thrown when a invalid date is used in Date class.
 * 
 * @author	Stefan Hahn
 */
public class DateFormatException extends IllegalArgumentException {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -323912157824179477L;
	
	/**
	 * Creates a new DateFormatException object.
	 * 
	 * @param		message				Message text
	 */
	public DateFormatException(String message) {
		super(message);
	}
}
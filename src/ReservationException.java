public class ReservationException extends Exception {

	/**
	 * Creates a new <code>ReservationException</code> object with given message.
	 * 
	 * @param		message					Message text
	 */
	public ReservationException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new <code>ReservationException</code> object with empty message.
	 */
	public ReservationException() {
		super();
	}
}


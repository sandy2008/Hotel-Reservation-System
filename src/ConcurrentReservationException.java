public class ConcurrentReservationException extends ReservationException {

	private Reservation concurringReservation;
	
	/**
	 * Creates a new <code>ConcurrentReservationException</code> object with given message
	 * and concurring {@link Reservation}.
	 * 
	 * @param	message					Message text
	 * @param	concurringReservation	{@link Reservation} blocking a new reservation
	 */
	public ConcurrentReservationException(String message, Reservation concurringReservation) {
		super(message);
		this.concurringReservation = concurringReservation;
	}
	
	/**
	 * Creates a new <code>ConcurrentReservationException</code> object with given
	 * concurring {@link Reservation}.
	 * 
	 * @param	concurringReservation	{@link Reservation} blocking a new reservation
	 */
	public ConcurrentReservationException(Reservation concurringReservation) {
		this("Concurred Reservation", concurringReservation);
	}
	
	/**
	 * @see		Throwable#getMessage()
	 * @see		Exception#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + ": " + this.concurringReservation.getID();
	}
}

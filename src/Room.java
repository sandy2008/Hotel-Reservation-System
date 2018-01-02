import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Room implements Showable{
	private String name = "";
	private double Price = 0;
	private int bedNumber = 0;
	private HashMap<UUID, Reservation> reservations = new HashMap<>();
	
	void addReservation(Reservation newReservation) throws ConcurrentReservationException{
		if (this.reservations.size() > 0) {
			Set<UUID> ids = this.reservations.keySet();
			
			for (UUID id: ids) {
				Date currentArrival = this.reservations.get(id).getArrivalDate();
				Date currentDeparture = this.reservations.get(id).getDepartureDate();
				Date newArrival = newReservation.getArrivalDate();
				Date newDeparture = newReservation.getDepartureDate();
				
				if ((!(newArrival.compareTo(currentArrival) > 0) || !(newDeparture.compareTo(currentArrival) <= 0))
						&& (!(newArrival.compareTo(currentDeparture) >= 0) || !(newDeparture.compareTo(currentDeparture) > 0))) {
						throw new ConcurrentReservationException(this.reservations.get(id));
					}
			}
	
		}
		
		this.reservations.put(newReservation.getID(), newReservation);
	}
	
	public boolean checkVacancy(Date checkin, Date checkout) {
		Set<UUID> ids = this.reservations.keySet();
		
		for (UUID id: ids) {
			Date currentArrival = this.reservations.get(id).getArrivalDate();
			Date currentDeparture = this.reservations.get(id).getDepartureDate();
			
			if ((!(checkin.compareTo(currentArrival) > 0) || !(checkout.compareTo(currentArrival) <= 0))
					&& (!(checkin.compareTo(currentDeparture) >= 0) || !(checkout.compareTo(currentDeparture) > 0))) {
					return false;
				}
		}
		
		return true;

	}
	
	public boolean checkUUID(UUID code) {
		Set<UUID> ids = this.reservations.keySet();
		
		for (UUID id: ids) {
			if(id.equals(code))
				return true;
		}
		
		return false;
	}
	
	public Reservation getReservation(UUID code) {
		Set<UUID> ids = this.reservations.keySet();
		
		for (UUID id: ids) {
			if(id.equals(code))
				return this.reservations.get(code);
		}
		
		return null;
	}

	
	public Room(String name, double basePrice, int bedNumber) {
		this.setName(name);
		this.setBasePrice(basePrice);
		this.setbedNumber(bedNumber);
	}
	
	void cancelReservation(Reservation reservation) throws ReservationException {
		if (!this.reservations.containsKey(reservation.getID())) {
			throw new ReservationException("No Such Reservation.");
		}
		
		this.reservations.remove(reservation.getID());
	}
	
	public void setName(String name) {
		if (name.equals("")) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setBasePrice(double Price) {
		if (Price <= 0) {
			throw new IllegalArgumentException("Base price muste be greater then 0.");
		}
		
		this.Price = Price;
	}
	
	public double getBasePrice() {
		return this.Price;
	}

	public void setbedNumber(int bedNumber) {
		if (bedNumber <= 0) {
			throw new IllegalArgumentException("Maximum person count cannot be lower then 1");
		}
		
		this.bedNumber = bedNumber;
	}
	
	public int getbedNumber() {
		return this.bedNumber;
	}
	
	public void printReservationInformation() {
		if (this.reservations.size() > 0) {
			Set<UUID> ids = this.reservations.keySet();
			
			for (UUID id: ids) {
				System.out.println(this.reservations.get(id).getInformation());
			}
		}
	}	
	public void show() {
		System.out.println("Type: " + this.getClass().getSimpleName());
		System.out.println("Name: " + this.getName());
		System.out.println("Price: " + this.getBasePrice());
		System.out.println("BedNumber: " + this.getbedNumber());
	}
	
	
}

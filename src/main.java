import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class main {
	public static void main(String[] args) {
		Date arrivalDate = null;
		int nights = 0;
		int persons = 0;
		
		System.out.println("--------------------------------------");
		System.out.println("Kingyu Hotel Reservation System V0.1");
		System.out.println("Souce Code:https://github.com/sandy2008/HotelReservationSystem");
		System.out.println("--------------------------------------");
		
		Vector<Room> rooms = new Vector<>(Arrays.asList(new Room[] {
				new SingleRoom("Single Room A", 10000, 1),
				new SingleRoom("Single Room B",  12000, 1)
		}));
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input Checkin Date.\n > ");
			arrivalDate = new Date(scanner.nextLine());
			
			System.out.print("Input Nights.\n > ");
			nights = Integer.parseInt(scanner.nextLine()) - 1;
			
			System.out.print("Input Person Number.\n > ");
			persons = Integer.parseInt(scanner.nextLine()) - 1;
			
			for (Room SingleRoom: rooms) {
				showObjects(new Reservation(arrivalDate, nights, persons, SingleRoom));
				showObjects(SingleRoom);
			}
			
			System.out.println("-------------------------------------------------");
		
		}

	}
	
	/**
	 * Calls {@link Showable#show() show()} method on given {@link Showable} object.
	 * 
	 * @param		showable	Any object implementing {@link Showable} interface.
	 */
	public static void showObjects(Showable showable) {
		System.out.println("-------------------------------------------------");
		showable.show();
	}
}

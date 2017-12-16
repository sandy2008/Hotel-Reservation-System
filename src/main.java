import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class main {
	public static void main(String[] args) {
		Date arrivalDate = null;
		int nights = 0;
		
		Vector<Room> rooms = new Vector<>(Arrays.asList(new Room[] {
				new SingleRoom("Single Room A", 100, 50),
				new SingleRoom("Single Room B",  150, 100)
		}));
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input Checkin Date.\n > ");
			arrivalDate = new Date(scanner.nextLine());
			
			System.out.print("Input Nights.\n > ");
			nights = Integer.parseInt(scanner.nextLine()) - 1;
			
			for (Room SingleRoom: rooms) {
				showObjects(new Reservation(arrivalDate, nights, 3, SingleRoom));
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

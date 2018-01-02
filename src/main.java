import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class main {
	private static final boolean True = false;

	public static void main(String[] args) {
		Date arrivalDate = null;
		String roomTypeString;

		int nights = 0;
		int persons = 0;
		
		System.out.println("--------------------------------------");
		System.out.println("Ubume Hotel Reservation System V0.2");
		System.out.println("Souce Code:https://github.com/sandy2008/HotelReservationSystem");
		System.out.println("--------------------------------------");
		
		Vector<Room> rooms = new Vector<>(Arrays.asList(new Room[] {
				new SingleRoom("Single Room A", 10000, 1),
				new SingleRoom("Single Room B",  12000, 1),
				new DoubleRoom("Double Room A",  20000, 1),
				new FamilyRoom("Family Room A",  25000, 1)
				
		}));
		
		System.out.println("1. User UI Reserve");
		System.out.println("2. User UI Search");
		System.out.println("3. Admin UI Add");
		System.out.println("4. Admin UI Show");
		System.out.println("5. Admin UI CheckIn");
		System.out.println("6. Admin UI CheckOut");
		System.out.println("-------------------------------------------------");
		
		System.out.print("Input Function.\n > ");

		
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				int select = Integer.parseInt(scanner.nextLine());
			if(select == 1){
			System.out.println("Welcome to User Reservation System");
			
			System.out.print("Input Room Type (Single/Double/Family).\n > ");
			roomTypeString = scanner.nextLine();

			System.out.print("Input Checkin Date.\n > ");
			arrivalDate = new Date(scanner.nextLine());
			
			System.out.print("Input Nights.\n > ");
			nights = Integer.parseInt(scanner.nextLine()) - 1;
			
			System.out.print("Input Person Number.\n > ");
			persons = Integer.parseInt(scanner.nextLine()) - 1;
			
			for (Room Room: rooms) {
				if(roomTypeString.equals("Single") ) {
				if(Room instanceof SingleRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				showObjects(new Reservation(arrivalDate, nights, persons, Room));
				break;
				}
				}
			}
				
				if(roomTypeString.equals("Double") ) {
				if(Room instanceof DoubleRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				showObjects(new Reservation(arrivalDate, nights, persons, Room));
				break;
				}
				}
			}
				
				if(roomTypeString.equals("Family") ) {
				if(Room instanceof FamilyRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				showObjects(new Reservation(arrivalDate, nights, persons, Room));
				break;
				}
				}
			}
				
			}

			}
			
			else if(select== 2){
			System.out.println("Welcome to User Search System");
			
			System.out.print("Input Room Type (Single/Double/Family).\n > ");
			roomTypeString = scanner.nextLine();

			System.out.print("Input Checkin Date.\n > ");
			arrivalDate = new Date(scanner.nextLine());
			
			System.out.print("Input Nights.\n > ");
			nights = Integer.parseInt(scanner.nextLine()) - 1;
			
			System.out.print("Input Person Number.\n > ");
			persons = Integer.parseInt(scanner.nextLine()) - 1;
			
			for (Room Room: rooms) {
				if(roomTypeString.equals("Single") ) {
				if(Room instanceof SingleRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				System.out.println("Empty!");
				break;
				}
				}
			}
				
				if(roomTypeString.equals("Double") ) {
				if(Room instanceof DoubleRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				System.out.println("Empty!");
				break;
				}
				}
			}
				
				if(roomTypeString.equals("Family") ) {
				if(Room instanceof FamilyRoom) {
				if(Room.checkVacancy(arrivalDate, arrivalDate.getFollowingDate(nights))== true){
				System.out.println("Empty!");
				break;
				}
				}
			}
				
			}

			}
			System.out.println("-------------------------------------------------");
			
			System.out.println("continue?");
			if(Integer.parseInt(scanner.nextLine())==1) {
				System.out.println("1. User UI Reserve");
				System.out.println("2. User UI Search");
				System.out.println("3. Admin UI Add");
				System.out.println("4. Admin UI Show");
				System.out.println("5. Admin UI CheckIn");
				System.out.println("6. Admin UI CheckOut");
				System.out.println("-------------------------------------------------");
				
				System.out.print("Input Function.\n > ");
			}
			else
				System.exit(1);
				
		}
		}
		
		catch (DateFormatException | ReservationException | NumberFormatException e) {
			System.err.println(e.getMessage());
			System.exit(1);
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

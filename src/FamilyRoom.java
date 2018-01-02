
public class FamilyRoom extends Room{
	public FamilyRoom(String name,double basePrice, int bedNumber) {
		super(name, basePrice, bedNumber);
	}
	public void show() {
		super.show();
		this.printReservationInformation();
	}
}

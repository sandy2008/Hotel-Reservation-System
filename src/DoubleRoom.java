
public class DoubleRoom extends Room{
	public DoubleRoom(String name,double basePrice, int bedNumber) {
		super(name, basePrice, bedNumber);
	}
	public void show() {
		super.show();
		this.printReservationInformation();
	}
}

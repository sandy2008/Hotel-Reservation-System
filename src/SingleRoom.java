
public class SingleRoom extends Room{
	public SingleRoom(String name,double basePrice, int bedNumber) {
		super(name, basePrice, bedNumber);
	}
	public void show() {
		super.show();
		this.printReservationInformation();
	}
}

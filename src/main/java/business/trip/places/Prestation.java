package business.trip.places;

public class Prestation {

	private String name;

	public Prestation(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}

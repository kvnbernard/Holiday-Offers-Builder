package business.trip.transports;

import java.math.BigDecimal;

public class LandTransport extends Transport{
	public LandTransport(String name, BigDecimal pricePerKm, int speed, double comfort) {
		super(name, pricePerKm, speed, comfort);
	}
	
	public boolean canCrossSea() {
		return false;
	}
}

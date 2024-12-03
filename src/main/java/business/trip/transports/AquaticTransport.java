package business.trip.transports;

import java.math.BigDecimal;

public class AquaticTransport extends Transport{
	
	public AquaticTransport(String name, BigDecimal pricePerKm, int speed, double comfort) {
		super(name, pricePerKm, speed, comfort);
	}
	
	public boolean canCrossSea() {
		return true;
	}
}

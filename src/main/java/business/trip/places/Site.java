package business.trip.places;

import java.math.BigDecimal;

public class Site extends Place {

	public Site(String name, Position position, BigDecimal price, boolean isSeaSided, boolean isIntoSea) {
		super(name, position, price, isSeaSided, isIntoSea);
	}
	
}

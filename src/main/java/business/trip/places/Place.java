package business.trip.places;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Place {
	private String name;
	private Position position;
	private BigDecimal price;
	private boolean isSeaSided;
	private boolean isIntoSea;
	
	public Place(String name, Position position, BigDecimal price, boolean isSeaSided, boolean isIntoSea) {
		super();
		this.name = name;
		this.position = Objects.requireNonNull(position, "Object 'position' cannot be null");
		this.price = Objects.requireNonNull(price, "Object 'price' cannot be null");
		this.isSeaSided = isSeaSided;
		this.isIntoSea = isIntoSea;
		
		if(name.isBlank()) {
			throw new IllegalArgumentException("Empty place name not allowed");
		}
		
		if(price.signum() == -1) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
	}
	
	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isSeaSided() {
		return isSeaSided;
	}

	public boolean isIntoSea() {
		return isIntoSea;
	}

	@Override
	public String toString() {
		return name + " - Price: " + price;
	}

	
	
}

package business.trip.transports;

import java.math.BigDecimal;

public abstract class Transport {

	private String name;
	private BigDecimal pricePerKm;
	private int speed;
	private double comfort;

	public Transport(String name, BigDecimal pricePerKm, int speed, double comfort) {
		this.name = name;
		this.pricePerKm = pricePerKm;
		this.speed = speed;
		this.comfort = comfort;

		if (name.isBlank()) {
			throw new IllegalArgumentException("Empty transport name not allowed");
		}

		if (speed <= 0.0) {
			throw new IllegalArgumentException("Speed cannot be negative");
		}

		if (pricePerKm.signum() == -1) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
	}

	/**
	 * Calculate the comfort rate weighted with a distance : the comfort decreases
	 * when the distance increases.
	 * 
	 * This method is calibrated to be used with Tahiti max distance possible (80km)
	 * 
	 * @param distance The distance (in kilometers) to weight comfort with
	 * @return
	 */
	public double comfortOverDistance(double distance) {
		double c = comfort - 3 * (distance / 80.0);
		return c > 0 ? c : 0.0;
	}

	public abstract boolean canCrossSea();

	public String getName() {
		return name;
	}

	public BigDecimal getPricePerKm() {
		return pricePerKm;
	}

	public int getSpeed() {
		return speed;
	}

	public double getComfort() {
		return comfort;
	}
	
	public String toString() {
		return name + " -- " + pricePerKm + " euros/km -- " + speed + " Km -- " + comfort + " Stars";
	}

}

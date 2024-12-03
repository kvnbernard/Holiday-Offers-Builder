package business.trip_finder.builder;

import java.math.BigDecimal;

public class BuilderInput {

	private int nbExcursions;
	private int nbDays;
	private double comfort;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public BuilderInput() {
		super();
	}

	public BuilderInput(int nbExcursions, int nbDays, double comfort, BigDecimal minPrice, BigDecimal maxPrice) {
		super();
		this.nbExcursions = nbExcursions;
		this.nbDays = nbDays;
		this.comfort = comfort;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getNbExcursions() {
		return nbExcursions;
	}

	public void setNbExcursions(int nbExcursions) {
		this.nbExcursions = nbExcursions;
	}

	public double getComfort() {
		return comfort;
	}

	public void setComfort(double comfort) {
		this.comfort = comfort;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getNbDays() {
		return nbDays;
	}

	public void setNbDays(int nbDays) {
		this.nbDays = nbDays;
	}
}

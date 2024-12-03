package business.trip_finder.filter;

import java.math.BigDecimal;

public class FilterParameters {

	private int nbDays;
	private int nbExcursions;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	public FilterParameters() {
		super();
	}

	public FilterParameters(int nbDays, int nbExcursions, BigDecimal minPrice, BigDecimal maxPrice) {
		super();
		this.nbDays = nbDays;
		this.nbExcursions = nbExcursions;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getNbDays() {
		return nbDays;
	}

	public void setNbDays(int nbDays) {
		this.nbDays = nbDays;
	}

	public int getNbExcursions() {
		return nbExcursions;
	}

	public void setNbExcursions(int nbExcursions) {
		this.nbExcursions = nbExcursions;
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

	@Override
	public String toString() {
		return "FilterParameters [nbDays=" + nbDays + ", nbExcursions=" + nbExcursions + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}
	
	
}

package business.trip_finder.best_trip_finder;

import java.math.BigDecimal;

public class PriceRange {

	private BigDecimal minPrice;

	private BigDecimal maxPrice;

	public PriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public PriceRange add(BigDecimal price) {
		BigDecimal min = getMinPrice();
		BigDecimal max = getMaxPrice();
		return new PriceRange(min.add(price), max.add(price));
	}
	
	public PriceRange substract(BigDecimal price) {
		BigDecimal min = getMinPrice();
		BigDecimal max = getMaxPrice();
		return new PriceRange(min.subtract(price), max.subtract(price));
	}
	
	public PriceRange addRatio(BigDecimal ratio) {
		BigDecimal min = getMinPrice();
		BigDecimal max = getMaxPrice();
		
		BigDecimal ratioMin = min.multiply(ratio);
		BigDecimal ratioMax = max.multiply(ratio);
		
		return new PriceRange(min.add(ratioMin), max.add(ratioMax));
	}
	
	public PriceRange substractRatio(BigDecimal ratio) {
		BigDecimal min = getMinPrice();
		BigDecimal max = getMaxPrice();
		
		BigDecimal ratioMin = min.multiply(ratio);
		BigDecimal ratioMax = max.multiply(ratio);
		
		return new PriceRange(min.subtract(ratioMin), max.subtract(ratioMax));
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
}

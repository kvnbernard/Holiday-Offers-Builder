package business.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Trip {
	private List<Day> days;
	private BigDecimal price;
	private double comfort;
	
	public Trip(List<Day> days) {
		this.days = days;
		this.price = computePrice(days);
		this.comfort = computeComfort(days);
	}
	
	private BigDecimal computePrice(List<Day> days) {
		return days.stream()
				.map(Day::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private double computeComfort(List<Day> days) {
		return days.stream()
				.mapToDouble(Day::getComfort)
				.average()
				.orElseThrow(() -> new RuntimeException("No Day in trip, should never happen..."));
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public List<Day> getDays() {
		return days;
	}
	
	public double getComfort() {
		return comfort;
	}

	@Override
	public String toString() {
		StringBuilder sbDays = new StringBuilder();
		days.forEach(sbDays::append);
		return "Trip [" + "Total price: " + price.setScale(2, RoundingMode.UP) 
		+ "€ - Comfort: " + BigDecimal.valueOf(comfort).setScale(2, RoundingMode.UP) + " - " + sbDays.toString() + "]\n";
	}
	
	
}

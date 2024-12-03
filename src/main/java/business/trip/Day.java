package business.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import business.trip.places.Hotel;

public class Day {
	private Hotel startHotel;
	private List<Excursion> excursions;
	private int date;
	private double comfort;
	private BigDecimal price;

	public Day(List<Excursion> excursions, int date) {
		super();
		this.excursions = excursions;
		this.startHotel = excursions.get(0).getDepartureHotel();
		this.date = date;
		this.comfort = calculateComfort();
		this.price = excursions.stream()
				.map(Excursion::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	/**
	 * A day with no excursions, only resting.
	 */
	public Day(Hotel hotel, int date) {
		this.excursions = List.of();
		this.startHotel = hotel;
		this.date = date;
		this.comfort = hotel.getComfort();
		this.price = hotel.getPrice();
	}
	
	private double calculateComfort() {
		if (excursions.isEmpty()) {
			return startHotel.getComfort();
		}
		return excursions.stream()
				.mapToDouble(Excursion::getComfort)
				.average()
				.orElseThrow();
	}

	public List<Excursion> getExcursions() {
		return excursions;
	}

	public int getDate() {
		return date;
	}

	public double getComfort() {
		return comfort;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Hotel getStartHotel() {
		return startHotel;
	}

	@Override
	public String toString() {
		StringBuilder sbExcursions = new StringBuilder();
		excursions.forEach(sbExcursions::append);
		return "\n\tDay n�" + date + " (Departure: " + startHotel.getName() + " - Comfort : "
		+ BigDecimal.valueOf(comfort).setScale(2, RoundingMode.UP) + " - " + price.setScale(2, RoundingMode.UP) + "� - \n" + sbExcursions.toString() + "\t)\n";
	}

	
}

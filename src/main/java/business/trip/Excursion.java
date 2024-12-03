package business.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import business.trip.places.Hotel;

public class Excursion {
	private Hotel departureHotel;
	private Hotel arrivalHotel;
	private List<Ride> rides;
	private BigDecimal price;
	private double comfort;
	private double totalDistance;

	public Excursion(Hotel departureHotel, Hotel arrivalHotel, List<Ride> rides) throws IllegalArgumentException {
		super();
		Objects.requireNonNull(departureHotel, "Object 'departureHotel'cannot be null.");
		Objects.requireNonNull(arrivalHotel, "Object 'arrivalHotel'cannot be null.");
		if (departureHotel.getName().equals(arrivalHotel.getName())) {
			Objects.requireNonNull(rides, "Object 'rides'cannot be null.");
			if (rides.isEmpty()) {
				throw new IllegalArgumentException("Cannot have an excursion ending in the same hotel without sites between");
			}
		}
		this.departureHotel = departureHotel;
		this.arrivalHotel = arrivalHotel;
		this.rides = rides;
		this.price = calculatePrice();

		this.comfort = calculateComfort();

		this.totalDistance = rides.stream()
				.mapToDouble(Ride::getDistance)
				.sum();
	}

	private double calculateComfort() {
		double ridesComfort = rides.stream()
				.mapToDouble(Ride::getComfort)
				.average()
				.orElseThrow(() -> new IllegalArgumentException("No ride to average from, should never happen..."));
		
		double sumComfort = ridesComfort + departureHotel.getComfort() * 2 + arrivalHotel.getComfort() * 2;
		double res = sumComfort / 5.0;
		return res;
	}

	private BigDecimal calculatePrice() {
		// The first hotel is never taken account in Rides, so we must count it here
		BigDecimal ridesPrice = rides.stream()
				.map(Ride::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal departurePrice = departureHotel.getPrice();
		return ridesPrice.add(departurePrice);
	}

	public Hotel getDepartureHotel() {
		return departureHotel;
	}

	public Hotel getArrivalHotel() {
		return arrivalHotel;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public double getComfort() {
		return comfort;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	@Override
	public String toString() {
		StringBuilder sbRides = new StringBuilder();
		rides.forEach(sbRides::append);
		return "\n\t\tExcursion {Departure: " + departureHotel.getName() + " - Arrival: " + arrivalHotel.getName() + " - "
		+ price.setScale(2, RoundingMode.UP) + "� - Comfort: " + BigDecimal.valueOf(comfort).setScale(2, RoundingMode.UP) + " - " 
		+ BigDecimal.valueOf(totalDistance).setScale(2, RoundingMode.UP) + "km - \n" + sbRides.toString()
				+ "\t\t}\n\n";
	}

	
}

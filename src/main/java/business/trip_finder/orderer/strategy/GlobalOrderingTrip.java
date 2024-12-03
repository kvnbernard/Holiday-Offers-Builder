package business.trip_finder.orderer.strategy;

import business.trip.Trip;

public class GlobalOrderingTrip {
	private double rate;
	private Trip trip;
	
	public GlobalOrderingTrip(double rate, Trip trip) {
		this.rate = rate;
		this.trip = trip;
	}

	public double getRate() {
		return rate;
	}

	public Trip getTrip() {
		return trip;
	}
	
}

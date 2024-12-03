package business.trip_finder.orderer.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import business.trip.Trip;
import business.trip_finder.orderer.OrderingStrategy;

public class GlobalStrategy implements OrderingStrategy{

	public static final double RATIO_PRICE = 0.5;
	public static final double RATIO_COMFORT = 0.5;

	public GlobalStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Trip> orderTrip(List<Trip> trips) {
		/* GlobalRatingTrip contains : 1. a trip - 2. rate of the trip */
		List<GlobalOrderingTrip> ratedTrips = new ArrayList<GlobalOrderingTrip>();
		
		double price, comfort, rate;
		double maxPrice = trips.stream()
				.max(Comparator.comparing(Trip::getPrice))
				.get()
				.getPrice()
				.doubleValue();
		
		for(Trip trip: trips) {
			price = trip.getPrice().doubleValue();
			comfort = trip.getComfort();
			rate = (price / maxPrice) * RATIO_PRICE + (comfort / 5) * RATIO_COMFORT;
			ratedTrips.add(new GlobalOrderingTrip(rate, trip));
		}
		
		ratedTrips = ratedTrips.stream()
				.sorted(Comparator.comparing(GlobalOrderingTrip::getRate))
				.collect(Collectors.toList());
		
		List<Trip> orderedTrips = ratedTrips.stream() 
				.map(GlobalOrderingTrip::getTrip)
				.collect(Collectors.toList());
		
		return orderedTrips;
	}

}

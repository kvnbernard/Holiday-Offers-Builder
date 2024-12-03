package business.trip_finder.orderer.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import business.trip.Trip;
import business.trip_finder.orderer.OrderingStrategy;

public class PriceAscendingStrategy implements OrderingStrategy{

	public PriceAscendingStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Trip> orderTrip(List<Trip> trips) {
		List<Trip> orderedAscTrips = trips.stream()
				.sorted(Comparator.comparing(Trip::getPrice))
				.collect(Collectors.toList());

		return orderedAscTrips;
	}

}

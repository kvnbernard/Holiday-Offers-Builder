package business.trip_finder.orderer.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import business.trip.Trip;
import business.trip_finder.orderer.OrderingStrategy;

public class ComfortAscendingStrategy implements OrderingStrategy{

	public ComfortAscendingStrategy() {
		// TODO Auto-generated constructor stub
	}

	public List<Trip> orderTrip(List<Trip> trips) {
		List<Trip> orderedAscTrips = trips.stream()
				.sorted(Comparator.comparing(Trip::getComfort))
				.collect(Collectors.toList());
		return orderedAscTrips;
	}

}

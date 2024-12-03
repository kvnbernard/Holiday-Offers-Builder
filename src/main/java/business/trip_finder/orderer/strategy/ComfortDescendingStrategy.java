package business.trip_finder.orderer.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import business.trip.Trip;
import business.trip_finder.orderer.OrderingStrategy;

public class ComfortDescendingStrategy implements OrderingStrategy{

	public ComfortDescendingStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Trip> orderTrip(List<Trip> trips) {
		List<Trip> orderedDescTrips = trips.stream()
				.sorted(Comparator.comparing(Trip::getComfort).reversed())
				.collect(Collectors.toList());

		return orderedDescTrips;
	}

}

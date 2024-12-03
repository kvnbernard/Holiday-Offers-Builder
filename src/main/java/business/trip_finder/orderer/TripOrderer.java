package business.trip_finder.orderer;

import java.util.List;

import business.trip.Trip;

public interface TripOrderer {
	
	List<Trip> orderTrips(List<Trip> trips, OrderingStrategyType type);
}

package business.trip_finder.orderer;

import java.util.List;

import business.trip.Trip;

public interface OrderingStrategy {
	
	List<Trip> orderTrip(List<Trip> trips);
	
}

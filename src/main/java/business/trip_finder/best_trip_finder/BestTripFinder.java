package business.trip_finder.best_trip_finder;

import java.util.List;

import business.trip.Trip;
import business.trip_finder.TripParameters;

public interface BestTripFinder {

	List<Trip> findBestTrips(TripParameters parameters);
}

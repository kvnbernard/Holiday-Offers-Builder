package business.trip_finder.filter;

import business.trip_finder.repository.PlacesUnion;

public interface TripFilter {

	/**
	 * Filter all places that the user would want to go, depending on his preferences.
	 * <p>
	 * Ex : A rich user will want to go in most expensives hotels
	 * 
	 * @param parameters
	 * @param placesUnion
	 * 
	 * @return A new instance of PlacesUnion with only satisfying places for the
	 *         user.
	 */
	PlacesUnion filterPlaces(FilterParameters parameters, PlacesUnion placesUnion);
}

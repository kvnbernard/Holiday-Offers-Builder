package business.trip_finder.path_finder;

/**
 * Helps to find the best path between a collection of places.
 * 
 * @author Aldric Vitali Silvestre
 */
public interface PathFinder {

	PlacesPath findPath(PlacesInput placesInput);
}

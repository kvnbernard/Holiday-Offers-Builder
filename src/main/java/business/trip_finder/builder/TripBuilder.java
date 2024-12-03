package business.trip_finder.builder;

import java.util.List;

import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip.transports.Transport;

/**
 * Builder pattern used to create a trip from a set of places.
 * <p>
 * "with..." methods are designed to return "this", the builder itself.
 * This permits to chain methods when defining the builder :
 * 
 * <pre>
 * {@code
 * TripBuilder builder = ...;
 * Trip trip = builder
 * 	.withHotels(hotels)
 * 	.withSites(sites)
 * 	.withTransports(transports)
 * 	.build();
 * }
 * </pre
 * 
 * @author Aldric Vitali Silvestre
 */
public interface TripBuilder {

	TripBuilder withHotels(List<Hotel> hotels);

	TripBuilder withSites(List<Site> sites);

	TripBuilder withTransports(List<Transport> transports);
	
	TripBuilder withInput(BuilderInput input);
	
	Trip build();
}

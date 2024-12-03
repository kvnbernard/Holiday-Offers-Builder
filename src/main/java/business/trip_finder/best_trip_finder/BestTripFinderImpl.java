package business.trip_finder.best_trip_finder;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip.transports.Transport;
import business.trip_finder.TripParameters;
import business.trip_finder.builder.BuilderInput;
import business.trip_finder.builder.TripBuilder;
import business.trip_finder.filter.FilterParameters;
import business.trip_finder.filter.TripFilter;
import business.trip_finder.orderer.OrderingStrategyType;
import business.trip_finder.orderer.TripOrderer;
import business.trip_finder.repository.PlacesUnion;
import business.trip_finder.repository.TripRepository;

public class BestTripFinderImpl implements BestTripFinder {

	private static Logger LOGGER = LoggerFactory.getLogger(BestTripFinderImpl.class);

	// ======= DEPENDENCIES =======
	private ParametersTweaker parametersTweaker;
	private TripRepository repository;
	private TripBuilder builder;
	private TripFilter filter;
	private TripOrderer orderer;

	@Override
	public List<Trip> findBestTrips(TripParameters parameters) {
		// The number of excursions depends on the comfort rate of the user (and of the
		// number of days of course)
		int nbExcursions = calculateNbExcursions(parameters.getNbDays(), parameters.getComfort());
		PlacesUnion placesUnion = fetchPlaces(parameters);
		// We can have no result here !
		if (placesUnion.getSites().isEmpty()) {
			LOGGER.warn("No site found with given query !");
			return List.of();
		}
		List<Transport> transports = repository.findAllTransports();

		// For each trip created, we will tweak a little the initial parameters, so that
		// all trips are (theorically) a bit different :
		List<TripParameters> parameterList = parametersTweaker.tweak(parameters);
		List<Trip> trips = parameterList.stream()
			.map(params -> buildTrip(nbExcursions, placesUnion, transports, params))
			.collect(Collectors.toList());
		
		// Filter with given strategy
		OrderingStrategyType orderingType = OrderingStrategyType.valueOf(parameters.getFilterBy());
		List<Trip> orderedTrips = orderer.orderTrips(trips, orderingType);
		
		return orderedTrips;
	}

	private Trip buildTrip(int nbExcursions, PlacesUnion placesUnion, List<Transport> transports, TripParameters tripParameters) {
		PlacesUnion placesFiltered = findPlacesUnion(tripParameters, placesUnion, nbExcursions);
		BuilderInput input = new BuilderInput(nbExcursions,
				tripParameters.getNbDays(),
				tripParameters.getComfort(),
				tripParameters.getMinPrice(),
				tripParameters.getMaxPrice());
		Trip tripBuilded = builder.withHotels(placesFiltered.getHotels())
			.withSites(placesFiltered.getSites())
			.withTransports(transports)
			.withInput(input)
			.build();
		return tripBuilded;
	}

	private int calculateNbExcursions(int nbDays, double comfort) {
		double coef = 0.04 * Math.pow(comfort, 2.0) - 0.5 * comfort + 2;
		return (int) Math.floor((double) nbDays * coef);
	}

	/**
	 * Fetch all sites and places corresponding to the user parameters and
	 * affordable by him.
	 * 
	 * @param nbExcursions
	 */
	private PlacesUnion findPlacesUnion(TripParameters parameters, PlacesUnion placesUnion, int nbExcursions) {
		int nbDays = parameters.getNbDays();
		BigDecimal minPrice = parameters.getMinPrice();
		BigDecimal maxPrice = parameters.getMaxPrice();
		FilterParameters filterParams = new FilterParameters(nbDays, nbExcursions, minPrice, maxPrice);
		return filter.filterPlaces(filterParams, placesUnion);
	}

	private PlacesUnion fetchPlaces(TripParameters parameters) {
		List<Hotel> hotels = repository.findAllHotels();
		List<Site> sites;
		if (parameters.getKeywords() == null || parameters.getKeywords().isBlank()) {
			// We can have ALL sites without issues
			sites = repository.findAllSites();
		} else {
			sites = repository.findRelevantSites(parameters.getKeywords());
		}
		PlacesUnion placesUnion = new PlacesUnion(hotels, sites);
		return placesUnion;
	}

	// ======= GETTERS / SETTERS =======
	public TripBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(TripBuilder tripBuilder) {
		this.builder = tripBuilder;
	}

	public TripFilter getFilter() {
		return filter;
	}

	public void setFilter(TripFilter tripFilter) {
		this.filter = tripFilter;
	}

	public TripOrderer getOrderer() {
		return orderer;
	}

	public void setOrderer(TripOrderer tripOrderer) {
		this.orderer = tripOrderer;
	}

	public TripRepository getRepository() {
		return repository;
	}

	public void setRepository(TripRepository tripRepository) {
		this.repository = tripRepository;
	}

	public ParametersTweaker getParametersTweaker() {
		return parametersTweaker;
	}

	public void setParametersTweaker(ParametersTweaker parametersTweaker) {
		this.parametersTweaker = parametersTweaker;
	}
}

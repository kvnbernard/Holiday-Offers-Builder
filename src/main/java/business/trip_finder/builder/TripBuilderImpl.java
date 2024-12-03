package business.trip_finder.builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.trip.Day;
import business.trip.Excursion;
import business.trip.Ride;
import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Place;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip.transports.Transport;
import business.trip_finder.path_finder.PathFinder;
import business.trip_finder.path_finder.PlacesInput;
import business.trip_finder.path_finder.PlacesPath;
import business.util.RandomUtils;

public class TripBuilderImpl implements TripBuilder {

	private static Logger LOGGER = LoggerFactory.getLogger(TripBuilderImpl.class);

	private static final int NB_SITES_PER_EXCURSION = 3;

	// Attributes given
	private List<Hotel> hotels;
	private List<Site> sites;
	private List<Transport> transports;
	private BuilderInput builderInput;

	// Algorithm attributes
	private List<Hotel> unusedHotels;
	private List<Site> unusedSites;
	private List<Transport> aquaticTransports;
	private List<Transport> landTransports;
	private boolean forceChangeHotel = false;

	// Dependencies
	private PathFinder pathFinder;

	@Override
	public Trip build() {
		// Small assertion here, the algorithm will not be valid else
		int nbExcursions = builderInput.getNbExcursions();
		int nbDays = builderInput.getNbDays();
		if (nbExcursions <= 0) {
			throw new IllegalArgumentException("The number of excursions is invalid : " + nbExcursions);
		}
		LOGGER.info("Create trip with {} excursions on {} days", nbExcursions, nbDays);
		initAlgorithmAttributes();

		List<Excursion> excursions = createExcursions();
		List<Day> days = createDays(excursions);
		return new Trip(days);
	}

	// ==== INIT ====
	private void initAlgorithmAttributes() {
		unusedSites = new ArrayList<>(sites);
		unusedHotels = new ArrayList<>(hotels);

		// Separate each transport group
		Map<Boolean, List<Transport>> transportGroups = transports.stream()
				.collect(Collectors.partitioningBy(Transport::canCrossSea));
		aquaticTransports = transportGroups.get(true);
		aquaticTransports.sort(Comparator.comparing(Transport::getComfort));
		landTransports = transportGroups.get(false);
		landTransports.sort(Comparator.comparing(Transport::getComfort));
	}

	// ==== DAYS ====
	private List<Day> createDays(List<Excursion> excursions) {
		int nbDays = builderInput.getNbDays();
		int nbExcursions = excursions.size();

		List<Day> days = new ArrayList<>();
		Hotel lastHotel = null;
		// We want to spread out the excursions over as many days as possible
		// If we don't have enough excursions, the last days will only be rest
		int currentExcursionIndex = 0;
		for (int dayIndex = 1; dayIndex <= nbDays; dayIndex++) {
			if (nbExcursions == 0) {
				// We must have at least one excursion at this moment
				days.add(new Day(lastHotel, dayIndex));
			} else {
				int nbExcursionsForDay = (int) Math.ceil((double) (nbExcursions) / (double) (nbDays - dayIndex + 1));
				nbExcursions -= nbExcursionsForDay;

				int newExcursionIndex = currentExcursionIndex + nbExcursionsForDay;
				List<Excursion> excursionsForDay = excursions.subList(currentExcursionIndex, newExcursionIndex);
				currentExcursionIndex = newExcursionIndex;

				// We want to remember the last hotel in case of we have a rest day after
				lastHotel = excursionsForDay.get(nbExcursionsForDay - 1).getArrivalHotel();

				days.add(new Day(excursionsForDay, dayIndex));
			}
		}

		return days;
	}

	// ==== EXCURSIONS ====
	private List<Excursion> createExcursions() {
		// Start all from a random hotel ==> multiple trips possible
		int randomHotelIndex = RandomUtils.randomInt(0, hotels.size());
		Hotel firstHotel = hotels.get(randomHotelIndex);

		List<Excursion> excursions = new ArrayList<>();
		// We don't want to be in the same hotel over and over
		for (int index = 0; index < builderInput.getNbExcursions(); index++) {
			LOGGER.info("Create Excursion {} / {}", (index + 1), builderInput.getNbExcursions());
			PlacesPath placesPath = findPlacesPath(firstHotel);

			updateUnusedLists(placesPath);

			Excursion excursion = createExcursionFromPath(placesPath);
			excursions.add(excursion);
		}
		return excursions;
	}

	private void updateUnusedLists(PlacesPath placesPath) {
		// We don't want to go to the same sites again
		unusedSites.removeAll(placesPath.getSitesBetween());
		unusedHotels.remove(placesPath.getDepartureHotel());
		unusedHotels.remove(placesPath.getArrivalHotel());

		// If they are empty, refill them
		if (unusedSites.size() < 3) {
			unusedSites.addAll(sites);
		}
		if (unusedHotels.size() < 1) {
			unusedHotels.addAll(hotels);
		}
	}

	private PlacesPath findPlacesPath(Hotel firstHotel) {
		List<Site> closestSites = findClosestSites(firstHotel, unusedSites);
		// 1/2 chances to end to the closest hotel after
		Hotel arrivalHotel;
		if (forceChangeHotel || RandomUtils.randomBool()) {
			forceChangeHotel = false;
			arrivalHotel = findClosestHotel(firstHotel, unusedHotels);
		} else {
			arrivalHotel = firstHotel;
			forceChangeHotel = true;
		}
		// Find the shortest path between all
		PlacesInput placesInput = new PlacesInput(firstHotel, closestSites, arrivalHotel);
		PlacesPath placesPath = pathFinder.findPath(placesInput);

		return placesPath;
	}

	private Excursion createExcursionFromPath(PlacesPath placesPath) {
		double wantedComfort = builderInput.getComfort();
		List<Place> fullPath = placesPath.getFullPath();
		// Create list of rides
		List<Ride> rides = new ArrayList<>();
		for (int index = 1; index < fullPath.size(); index++) {
			Place departurePlace = fullPath.get(index - 1);
			Place arrivalPlace = fullPath.get(index);

			Position departurePos = departurePlace.getPosition();
			Position arrivalPos = arrivalPlace.getPosition();
			double distance = departurePos.computeDistance(arrivalPos);

			// We maybe need an aquatic transport here
			List<Transport> transportListUsed;
			if (departurePlace.isIntoSea() || arrivalPlace.isIntoSea()) {
				transportListUsed = aquaticTransports;
			} else {
				transportListUsed = landTransports;
			}

			Transport transportUsed = findRightTransport(wantedComfort, distance, transportListUsed);

			Ride ride = new Ride(departurePlace, arrivalPlace, transportUsed);
			rides.add(ride);
		}
		return new Excursion(placesPath.getDepartureHotel(), placesPath.getArrivalHotel(), rides);
	}

	private Transport findRightTransport(double wantedComfort, double distance, List<Transport> transportListUsed) {
		// The choice is based on the comfort of the user, as we stated that transport
		// cost is negligeable over hotels etc.
		if (distance < 0.3) {
			return transportListUsed.get(0);
		}
		
		List<Double> comforts = transportListUsed.stream()
				.map(t -> t.comfortOverDistance(distance))
				.collect(Collectors.toList());
		int idxComfort = 0;
		for (double comfort : comforts) {
			idxComfort++;
			if (comfort > wantedComfort) {
				break;
			}
		}
		idxComfort--;
		Transport transportUsed = transportListUsed.get(idxComfort);
		return transportUsed;
	}

	private List<Site> findClosestSites(Place target, List<Site> siteList) {
		return siteList.stream()
				.sorted(Comparator.comparing(distanceWithTarget(target)))
				.limit(NB_SITES_PER_EXCURSION)
				.collect(Collectors.toList());
	}

	private Hotel findClosestHotel(Hotel target, List<Hotel> hotelList) {
		return hotelList.stream()
				.filter(h -> h != target)
				.min(Comparator.comparing(distanceWithTarget(target)))
				.orElseThrow();
	}

	private Function<Place, Double> distanceWithTarget(Place target) {
		return s -> {
			return target.getPosition().computeDistance(s.getPosition());
		};
	}

	// ==== BUILDER PATTERN METHODS ====
	@Override
	public TripBuilder withHotels(List<Hotel> hotels) {
		this.hotels = hotels;
		return this;
	}

	@Override
	public TripBuilder withSites(List<Site> sites) {
		this.sites = sites;
		return this;
	}

	@Override
	public TripBuilder withTransports(List<Transport> transports) {
		this.transports = transports;
		return this;
	}

	@Override
	public TripBuilder withInput(BuilderInput input) {
		this.builderInput = input;
		return this;
	}

	public PathFinder getPathFinder() {
		return pathFinder;
	}

	public void setPathFinder(PathFinder pathFinder) {
		this.pathFinder = pathFinder;
	}

}

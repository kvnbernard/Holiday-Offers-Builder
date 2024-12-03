package business.trip_finder.filter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip_finder.repository.PlacesUnion;
import business.util.PriceUtils;

public class TripFilterImpl implements TripFilter {
	
	private static final int NB_BEACHES_TO_KEEP = 2;

	private static Logger LOGGER = LoggerFactory.getLogger(TripFilterImpl.class);

	private static final int PERCENTAGE_COSTS_HOTEL = 70;
	private static final int PERCENTAGE_COSTS_SITES = 20;
	private static final int PERCENTAGE_COSTS_TRANSPORTS = 10;

	@Override
	public PlacesUnion filterPlaces(FilterParameters parameters, PlacesUnion placesUnion) {
		LOGGER.info("Filter " + placesUnion.getHotels().size() + " hotels and " + placesUnion.getSites().size() +" sites");
		List<Hotel> hotels = findHotels(placesUnion, parameters);
		List<Site> sites = findSites(placesUnion, parameters);
		
		if (sites.size() > 3) {
			// we don't want all beaches to be there
			List<Site> beaches = sites.stream()
				.filter(s -> s.getName().toUpperCase().startsWith("BEACH"))
				.collect(Collectors.toList());
			List<Site> beachesToKeep = beaches.stream()
				.unordered()
				.limit(NB_BEACHES_TO_KEEP)
				.collect(Collectors.toList());
			
			beaches.stream()
				.filter(b -> !beachesToKeep.contains(b))
				.forEach(sites::remove);
		}
		
		LOGGER.info("Got " + hotels.size() + " hotels and " + sites.size() + " sites at the end");
		return new PlacesUnion(hotels, sites);
	}

	private List<Hotel> findHotels(PlacesUnion placesUnion, FilterParameters parameters) {
		// Theorically, we can use 2 hotels per excursion
		int nbHotelUsed = parameters.getNbExcursions() * 2;

		BigDecimal maxHotelPrice = PriceUtils.percentage(parameters.getMaxPrice(), BigDecimal.valueOf(PERCENTAGE_COSTS_HOTEL));
		BigDecimal minHotelPrice = PriceUtils.percentage(parameters.getMinPrice(), BigDecimal.valueOf(PERCENTAGE_COSTS_HOTEL));

		List<Hotel> hotels = placesUnion.getHotels();

		BigDecimal allHotelsSum = hotels.stream()
				.map(Hotel::getPrice)
				.reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);

		int nbHotels = hotels.size();
		BigDecimal averageHotelPrice = allHotelsSum.divide(BigDecimal.valueOf(nbHotels), RoundingMode.HALF_UP);

		BigDecimal theoricalHotelCost = averageHotelPrice.multiply(BigDecimal.valueOf(nbHotelUsed));
		if (maxHotelPrice.compareTo(theoricalHotelCost) >= 0) {
			return findHotelsHighBudget(nbHotelUsed, maxHotelPrice, minHotelPrice, hotels);

		} else {
			return findHotelsLowBudget(nbHotelUsed, maxHotelPrice, hotels);
		}
	}

	private List<Hotel> findHotelsHighBudget(int nbHotelUsed, BigDecimal maxHotelPrice, BigDecimal minHotelPrice, List<Hotel> hotels) {
		// User is rich enough to have the most expensive hotels ?
		// Remove cheapest hotels until : 1. we have not enough hotels 2. average price
		// is too high
		List<Hotel> hotelSortedByPriceAsc = hotels
				.stream()
				.sorted(Comparator.comparing(Hotel::getPrice))
				.collect(Collectors.toList());
		int nHotels = hotelSortedByPriceAsc.size();
		int nbHotelsToRemove = 1;
		int currentNbHotels = nHotels - nbHotelsToRemove;
		while (currentNbHotels >= nbHotelUsed) {
			BigDecimal avg = hotelSortedByPriceAsc.stream()
					.map(Hotel::getPrice)
					.skip(nbHotelsToRemove)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			avg = avg.divide(BigDecimal.valueOf(currentNbHotels), RoundingMode.HALF_UP);
			BigDecimal price = avg.multiply(BigDecimal.valueOf(nbHotelUsed));
			// if price between min and max, we are good too
			if (price.compareTo(maxHotelPrice) == 1 || PriceUtils.isBetween(price, minHotelPrice, maxHotelPrice)) {
				break;
			}
			nbHotelsToRemove++;
			currentNbHotels = nHotels - nbHotelsToRemove;
		}
		// We go out of the loop because we removed one hotel too many
		nbHotelsToRemove--;
		return hotelSortedByPriceAsc.stream()
				.skip(nbHotelsToRemove)
				.collect(Collectors.toList());
	}

	private List<Hotel> findHotelsLowBudget(int nbHotelUsed, BigDecimal maxHotelPrice, List<Hotel> hotels) {
		// User cannot afford expensive hotels
		// Remove most expensives hotels until : 1. we have only 1 hotel (the cheapest
		// one) 2. average price is good enough
		List<Hotel> hotelSortedByPriceDesc = hotels
				.stream()
				.sorted(Comparator.comparing(Hotel::getPrice).reversed())
				.collect(Collectors.toList());

		int nHotels = hotelSortedByPriceDesc.size();
		int nbHotelsToRemove = 1;
		int currentNbHotels = nHotels - nbHotelsToRemove;
		while (currentNbHotels > 1) {
			BigDecimal avg = hotelSortedByPriceDesc.stream()
					.map(Hotel::getPrice)
					.skip(nbHotelsToRemove) // remove most expensive first
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			avg = avg.divide(BigDecimal.valueOf(currentNbHotels), RoundingMode.HALF_UP);
			BigDecimal price = avg.multiply(BigDecimal.valueOf(nbHotelUsed));
			// if price between min and max, we are good
			if (price.compareTo(maxHotelPrice) == -1) {
				break;
			}
			nbHotelsToRemove++;
			currentNbHotels = nHotels - nbHotelsToRemove;
		}
		return hotelSortedByPriceDesc.stream()
				.skip(nbHotelsToRemove)
				.collect(Collectors.toList());
	}

	private List<Site> findSites(PlacesUnion placesUnion, FilterParameters parameters) {
		BigDecimal maxSitesPrice = PriceUtils.percentage(parameters.getMaxPrice(), BigDecimal.valueOf(PERCENTAGE_COSTS_SITES));

		List<Site> sites = placesUnion.getSites();
		BigDecimal priceAllSites = PriceUtils.sumPricesInObject(sites, Site::getPrice);

		// Only case is if the sum of all sites is too high for the user,
		// in this case, we remove the most expensive ones until we reach the right
		// price
		if (priceAllSites.compareTo(maxSitesPrice) == 1) {
			List<Site> sitesByPriceDesc = sites
					.stream()
					.sorted(Comparator.comparing(Site::getPrice).reversed())
					.collect(Collectors.toList());

			int nbSitesToRemove = 1;
			while (true) {
				priceAllSites = sitesByPriceDesc.stream()
						.skip(nbSitesToRemove)
						.map(Site::getPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				if (priceAllSites.compareTo(maxSitesPrice) <= 0) {
					break;
				}
				nbSitesToRemove++;
			}
			return sitesByPriceDesc.stream()
					.skip(nbSitesToRemove)
					.collect(Collectors.toList());
		}
		// defensive copy in case of.
		return new ArrayList<>(sites);
	}
}

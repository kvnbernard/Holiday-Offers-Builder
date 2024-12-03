package business.trip_finder.path_finder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.paukov.combinatorics3.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.trip.places.Site;

public class BruteForcePathFinder implements PathFinder {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BruteForcePathFinder.class);

	@Override
	public PlacesPath findPath(PlacesInput placesInput) {
		Map<Double, List<Site>> possibleTrips = new HashMap<Double, List<Site>>();
		Double totalDistance = 0.0;
		
		List<Site> sites = placesInput.getSites();
		
		/* 
		 * Simple permutations of the list of sites making every combinations of sites possible
		 * without repetitions. For more info, check the combinatoricsLib3 on github.
		 * */
		List<List<Site>> collectedSites = Generator.permutation(sites)
		  .simple()
		  .stream()
		  .collect(Collectors.toList());
		
		LOGGER.info(collectedSites.size() + " combinaisons made out of given sites.");
		
		for(List<Site> sites2: collectedSites) {
			if(sites2.size() > 1) {
				totalDistance += placesInput.getDepartureHotel().getPosition().computeDistance(sites2.get(0).getPosition());
				for(int index = 0; index < sites2.size()-1; index++) {
					totalDistance += sites2.get(index).getPosition().computeDistance(sites2.get(index+1).getPosition());
				}
				totalDistance += sites2.get(sites2.size()-1).getPosition().computeDistance(placesInput.getArrivalHotel().getPosition());
				possibleTrips.put(totalDistance, sites2);
			}
			else {
				totalDistance += placesInput.getDepartureHotel().getPosition().computeDistance(sites2.get(0).getPosition());
				totalDistance += sites2.get(0).getPosition().computeDistance(placesInput.getArrivalHotel().getPosition());
				possibleTrips.put(totalDistance, sites2);
			}
			totalDistance = 0.0;
		}
		
		return new PlacesPath(placesInput.getDepartureHotel(), getLowestKeyMap(possibleTrips), placesInput.getArrivalHotel());
	}
	
	private List<Site> getLowestKeyMap(Map<Double, List<Site>> map){
		Double lowestValue = Double.POSITIVE_INFINITY;
		for(Double value: map.keySet()) {
			if(value < lowestValue) {
				lowestValue = value;
			}
		}
		return map.get(lowestValue);
	}
}

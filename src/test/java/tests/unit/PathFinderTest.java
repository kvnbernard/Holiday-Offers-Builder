package tests.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip_finder.path_finder.BruteForcePathFinder;
import business.trip_finder.path_finder.PathFinder;
import business.trip_finder.path_finder.PlacesInput;
import business.trip_finder.path_finder.PlacesPath;

/**
 * From a collection of places, the exact same path must be found
 * 
 * @author Aldric Vitali Silvestre
 */
public class PathFinderTest {
	
	private final Hotel hotel1 = new Hotel("hotel 1", new Position(-17.692665267514936, -149.30442982842237), BigDecimal.ONE, List.of());
	private final Hotel hotel2 = new Hotel("hotel 2", new Position(-17.611202981511816, -149.303743182977), BigDecimal.ONE, List.of());

	// Not ordered, in order to see if the algorithm do something
	// Right order : site 3 -> site 1 -> site 2
	private final List<Site> sitesTest2Hotels = List.of(
			new Site("site 1", new Position(-17.664826932078398, -149.30949926233527), BigDecimal.ONE, true, false),
			new Site("site 2", new Position(-17.645361384267744, -149.30984258505796), BigDecimal.ONE, true, false),
			new Site("site 3", new Position(-17.682491320743456, -149.30606603510842), BigDecimal.ONE, true, false));
	
	private final List<Site> sitesTest1Hotel = List.of(
			new Site("site 1", new Position(-17.72566362669506, -149.3196272879307), BigDecimal.ONE, true, false),
			new Site("site 2", new Position(-17.677911831785384, -149.40700292313335), BigDecimal.ONE, true, false),
			new Site("site 3", new Position(-17.665317633718615, -149.3091559454819), BigDecimal.ONE, true, false));

	private PathFinder finder = new BruteForcePathFinder();

	@Test
	public void findRightPath2Hotels() {
		PlacesInput in = new PlacesInput(hotel1, sitesTest2Hotels, hotel2);
		PlacesPath path = finder.findPath(in);
		PlacesPath target = new PlacesPath(hotel1, List.of(sitesTest2Hotels.get(2), sitesTest2Hotels.get(0), sitesTest2Hotels.get(1)), hotel2);
		assertPathsEqual(path, target);
	}

	@Test
	public void findRightPath1Hotel() {
		PlacesInput in = new PlacesInput(hotel1, sitesTest1Hotel, hotel1);
		PlacesPath path = finder.findPath(in);
		PlacesPath target = new PlacesPath(hotel1, List.of(sitesTest1Hotel.get(2), sitesTest1Hotel.get(1), sitesTest1Hotel.get(0)), hotel1);
		assertPathsEqual(path, target);
	}

	private void assertPathsEqual(PlacesPath toTest, PlacesPath target) {
		assertEquals(target.getDepartureHotel(), toTest.getDepartureHotel());
		assertEquals(target.getArrivalHotel(), toTest.getArrivalHotel());
		
		List<Site> targetSites = target.getSitesBetween();
		List<Site> testSites = toTest.getSitesBetween();
		for(int index = 0; index < testSites.size(); index++){
			Site testSite = testSites.get(index);
			Site targetSite = targetSites.get(index);
			
			assertEquals(targetSite.getName(), testSite.getName());
		}
	}
}

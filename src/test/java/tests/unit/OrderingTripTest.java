package tests.unit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import business.trip.Day;
import business.trip.Excursion;
import business.trip.Ride;
import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Site;
import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip_finder.orderer.OrderingStrategyType;
import business.trip_finder.orderer.TripOrderer;
import business.trip_finder.orderer.TripOrdererImpl;

public class OrderingTripTest {
	private final Hotel hotel1 = new Hotel("Hotel 1", new Position(-17.63907058284274, -149.4332122218146), BigDecimal.valueOf(59.99), List.of());
	private final Hotel hotel2 = new Hotel("Hotel 2", new Position(-17.6394, -149.4229), BigDecimal.valueOf(89.99), List.of());
	private final Hotel hotel3 = new Hotel("Hotel 3", new Position(-17.734856559589605, -149.22522197746633), BigDecimal.valueOf(70.99), List.of());


	private final Site site1 = new Site("site 1", new Position(-17.72566362669506, -149.3196272879307), BigDecimal.ONE, true, false);
	private final Site site2 = new Site("site 2", new Position(-17.677911831785384, -149.40700292313335), BigDecimal.ONE, true, false);
	private final Site site3 = new Site("site 3", new Position(-17.665317633718615, -149.3091559454819), BigDecimal.ONE, true, false);
	private final Site site4 = new Site("site 4", new Position(-17.74387167609863, -149.27433665195267), BigDecimal.ONE, true, false);
	private final Site site5 = new Site("site 5", new Position(-17.73322957873866, -149.2720803692436), BigDecimal.ONE, true, false);
	private final Site site6 = new Site("site 6", new Position(-17.775269594219832, -149.25697258638095), BigDecimal.ONE, true, false);
	
	private final LandTransport autobus = new LandTransport("Autobus", BigDecimal.valueOf(0.10), 50, 3.1);
	private final LandTransport foot = new LandTransport("Feet", BigDecimal.valueOf(0), 7,2.0);
	private final AquaticTransport boat = new AquaticTransport("Boat", BigDecimal.valueOf(0.20), 70,4.6);

	private final List<Ride> rides = List.of(
			new Ride(hotel3, site4, boat),
			new Ride(site4, site5, autobus),
			new Ride(site5, hotel3, foot)
			);

	private final List<Ride> rides2 = List.of(
			new Ride(hotel1, site2, autobus),
			new Ride(site2, site1, autobus),
			new Ride(site1, hotel2, autobus)
			);

	private final List<Ride> rides3 = List.of(
			new Ride(hotel2, site3, boat),
			new Ride(site3, site2, autobus),
			new Ride(site2, hotel2, foot)
			);
	private final List<Ride> rides4 = List.of(
			new Ride(hotel3, site2, foot),
			new Ride(site2, site6, foot),
			new Ride(site6, hotel2, foot)
			);
	
	private final List<Ride> rides5 = List.of(
			new Ride(hotel3, site1, autobus),
			new Ride(site1, site2, boat),
			new Ride(site2, hotel1, autobus)
			);
	private final List<Excursion> excursions = List.of(
			new Excursion(hotel3,hotel3,rides)
			);

	private final List<Excursion> excursions2 = List.of(
			new Excursion(hotel1,hotel2,rides2)
			);

	private final List<Excursion> excursions3 = List.of(
			new Excursion(hotel2,hotel2,rides3)
			);
	private final List<Excursion> excursions4 = List.of(
			new Excursion(hotel3,hotel2,rides4)
			);
	
	private final List<Excursion> excursions5 = List.of(
			new Excursion(hotel3,hotel1,rides5)
			);
	private final List<Day> days = List.of( 
			new Day(excursions, 1),
			new Day(excursions2, 2),
			new Day(excursions4, 3)
			);

	private final List<Day> days2 = List.of( 
			new Day(excursions5, 1),
			new Day(excursions3, 2),
			new Day(excursions, 3)
			);

	private final List<Day> days3 = List.of( 
			new Day(excursions2, 1),
			new Day(excursions, 2),
			new Day(excursions5, 3)
			);
	
	private final List<Trip> trips = List.of(
			new Trip(days),
			new Trip(days2),
			new Trip(days3)
			);
	@Test
	public void sortedListTripsComAsc() {
		TripOrderer toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips, OrderingStrategyType.COMFORT_ASCENDING);
		assertTripsEqual(orderedList, List.of(trips.get(0), trips.get(2), trips.get(1)));
	}
	
	@Test
	public void sortedListTripsComDesc() {
		TripOrderer toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips, OrderingStrategyType.COMFORT_DECSCENDING);
		assertTripsEqual(orderedList, List.of(trips.get(1), trips.get(2), trips.get(0)));
	}
	
	@Test
	public void sortedListTripsPriceAsc() {
		TripOrderer toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips, OrderingStrategyType.PRICE_ASCENDING);
		assertTripsEqual(orderedList, List.of(trips.get(2), trips.get(0), trips.get(1)));
	}
	
	@Test
	public void sortedListTripsPriceDesc() {
		TripOrderer toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips, OrderingStrategyType.PRICE_DECSCENDING);
		assertTripsEqual(orderedList, List.of(trips.get(1), trips.get(0), trips.get(2)));
	}
	
	@Test
	public void sortedListTripsGlobal() {
		TripOrderer toi = new TripOrdererImpl();
		List<Trip> orderedList = toi.orderTrips(trips, OrderingStrategyType.GLOBAL);
		assertTripsEqual(orderedList, List.of(trips.get(0), trips.get(2), trips.get(1)));
	}
	
	private void assertTripsEqual(List<Trip> orderedTrips, List<Trip> expectedTrips) {
		for(int index = 0; index < orderedTrips.size(); index++) {
			Trip orderedTrip = orderedTrips.get(index);
			Trip expectedTrip = expectedTrips.get(index);
			
			assertEquals(orderedTrip.getPrice(), expectedTrip.getPrice());
		}
	}
}

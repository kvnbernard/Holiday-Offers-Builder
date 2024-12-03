package tests.manual;

import java.math.BigDecimal;
import java.util.List;

import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip_finder.filter.FilterParameters;
import business.trip_finder.filter.TripFilter;
import business.trip_finder.filter.TripFilterImpl;
import business.trip_finder.repository.PlacesUnion;
import business.trip_finder.repository.TripRepository;
import mocks.dao.MockHotelDao;
import mocks.dao.MockSiteDao;
import mocks.dao.MockTransportDao;

public class TripFilterTest {

	public static final double LOW_PRICE_MIN = 100;
	public static final double LOW_PRICE_MAX = 200;
	public static final double HIGH_PRICE_MIN = 700;
	public static final double HIGH_PRICE_MAX = 1000;
	public static final int NB_DAYS = 2;
	public static final int NB_EXCURSION = 1;

	public static void main(String[] args) {
		testLowBudget();
		testHighBudget();
	}

	private static void testLowBudget() {
		System.out.println("======= WITH LOW BUDGET =======");
		TripRepository tripRepository = new TripRepository(new MockHotelDao(), new MockSiteDao(),
				new MockTransportDao());

		List<Hotel> hotels = tripRepository.findAllHotels();
		List<Site> sites = tripRepository.findRelevantSites("");

		PlacesUnion pu1 = new PlacesUnion(hotels, sites);

		FilterParameters fp1 = new FilterParameters(NB_DAYS, NB_EXCURSION, BigDecimal.valueOf(LOW_PRICE_MIN),
				BigDecimal.valueOf(LOW_PRICE_MAX));
		System.out.println("With parameters : " + fp1.toString());

		TripFilter tf = new TripFilterImpl();
		PlacesUnion puRes = tf.filterPlaces(fp1, pu1);
		System.out.println(puRes.toString());
	}
	
	private static void testHighBudget() {
		System.out.println("======= WITH HIGH BUDGET =======");
		TripRepository tripRepository = new TripRepository(new MockHotelDao(), new MockSiteDao(),
				new MockTransportDao());
		
		List<Hotel> hotels = tripRepository.findAllHotels();
		List<Site> sites = tripRepository.findRelevantSites("");
		
		PlacesUnion pu1 = new PlacesUnion(hotels, sites);
		
		FilterParameters fp1 = new FilterParameters(NB_DAYS, NB_EXCURSION, BigDecimal.valueOf(HIGH_PRICE_MIN),
				BigDecimal.valueOf(HIGH_PRICE_MAX));
		System.out.println("With parameters : " + fp1.toString());
		
		TripFilter tf = new TripFilterImpl();
		PlacesUnion puRes = tf.filterPlaces(fp1, pu1);
		System.out.println(puRes.toString());
	}

}

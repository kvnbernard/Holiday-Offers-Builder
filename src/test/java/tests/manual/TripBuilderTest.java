package tests.manual;

import java.math.BigDecimal;
import java.util.List;

import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip.transports.Transport;
import business.trip_finder.builder.BuilderInput;
import business.trip_finder.builder.TripBuilderImpl;
import business.trip_finder.path_finder.BruteForcePathFinder;
import business.trip_finder.repository.TripRepository;
import mocks.dao.MockHotelDao;
import mocks.dao.MockSiteDao;
import mocks.dao.MockTransportDao;

public class TripBuilderTest {

	public static void main(String[] args) {
		TripRepository repository = new TripRepository(new MockHotelDao(), new MockSiteDao(), new MockTransportDao());
		
		TripBuilderImpl builder = new TripBuilderImpl();
		builder.setPathFinder(new BruteForcePathFinder());
		
		List<Hotel> hotels = repository.findAllHotels();
		List<Transport> transports = repository.findAllTransports();
		List<Site> sites = repository.findRelevantSites("");
		
		BuilderInput input = new BuilderInput(3, 2, 3.4, BigDecimal.valueOf(800.0), BigDecimal.valueOf(1500.0));
		
		Trip trip = builder.withHotels(hotels)
			.withSites(sites)
			.withTransports(transports)
			.withInput(input)
			.build();
		System.out.println(trip.toString());
		//System.out.println(trip.getPrice());
	}
}

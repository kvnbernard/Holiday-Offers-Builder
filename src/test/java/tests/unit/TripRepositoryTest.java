package tests.unit;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import business.trip.places.Hotel;
import business.trip_finder.repository.TripRepository;
import mocks.dao.MockHotelDao;
import mocks.dao.MockSiteDao;
import mocks.dao.MockTransportDao;

public class TripRepositoryTest {

	private TripRepository tripRepository = new TripRepository(
			new MockHotelDao(),
			new MockSiteDao(),
			new MockTransportDao());
	
	@Test
	public void testHotelList() {
		List<Hotel> hotels = tripRepository.findAllHotels();
		assertThat(hotels, Matchers.hasSize(6));
		assertThat(hotels.get(0).getName(), Matchers.is("Hotel 1"));
	}
}

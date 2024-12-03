package mocks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.trip.Day;
import business.trip.Excursion;
import business.trip.Ride;
import business.trip.Trip;
import business.trip.places.Hotel;
import business.trip.places.Position;
import business.trip.places.Prestation;
import business.trip.places.Site;
import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip_finder.TripParameters;
import business.trip_finder.best_trip_finder.BestTripFinder;

public class MockBestTripFinder implements BestTripFinder {

	@Override
	public List<Trip> findBestTrips(TripParameters parameters) {		
		
		Prestation pr = new Prestation("a");
		
		Hotel hotel1 = new Hotel("Hotel 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), List.of(pr, pr, pr, pr));
		Hotel hotel2 = new Hotel("Hotel 2", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), List.of(pr, pr));
		
		Site site1 = new Site("Site 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), true, false);
		LandTransport autobus = new LandTransport("Autobus", BigDecimal.valueOf(0.10), 50, 3.1);
		LandTransport feet = new LandTransport("Feet", BigDecimal.valueOf(0), 7,2.0);
		AquaticTransport boat = new AquaticTransport("Boat", BigDecimal.valueOf(0.20), 70,4.6);
		
		
		List<Ride> rides = List.of(
				new Ride(hotel1, site1, boat),
				new Ride(site1, hotel2, autobus)
		);
		
		List<Excursion> excursions = List.of(
					new Excursion(hotel1,hotel2,rides),
					new Excursion(hotel2,hotel1,rides),
					new Excursion(hotel1,hotel2,rides),
					new Excursion(hotel1,hotel2,rides),
					new Excursion(hotel1,hotel2,rides)
				);
		
		List<Day> days = List.of( 
					new Day(excursions, 1),
					new Day(excursions, 2)
				);
		
		List<Trip> trips = List.of(
				new Trip(days),
				new Trip(days),
				new Trip(days)
 		);
		
		return trips;
	}

}

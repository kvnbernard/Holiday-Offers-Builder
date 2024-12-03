package dao;

import java.math.BigDecimal;
import java.util.Iterator;

import business.trip.places.Hotel;

public interface HotelDao {

	Iterator<Hotel> findAll();	
	
	Iterator<Hotel> findWithMinimum(BigDecimal minimumPrice);
	
}

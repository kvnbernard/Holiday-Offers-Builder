package business.trip_finder.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import business.trip.places.Hotel;
import business.trip.places.Site;
import business.trip.transports.Transport;
import dao.HotelDao;
import dao.SiteDao;
import dao.TransportDao;

public class TripRepository {
	
	private HotelDao hotelDao;
	private SiteDao siteDao;
	private TransportDao transportDao;
	
	public TripRepository() {
		super();
	}

	public TripRepository(HotelDao hotelDao, SiteDao siteDao, TransportDao transportDao) {
		super();
		this.hotelDao = hotelDao;
		this.siteDao = siteDao;
		this.transportDao = transportDao;
	}

	public List<Hotel> findAllHotels() {
		Iterator<Hotel> allHotels = hotelDao.findAll();
		return toList(allHotels);
	}

	public List<Transport> findAllTransports() {
		Iterator<Transport> allTransports = transportDao.findAll();
		return toList(allTransports);
	}
	
	public List<Site> findAllSites() {
		Iterator<Site> allSites = siteDao.findAll();
		return toList(allSites);
	}
	
	public List<Site> findRelevantSites(String query) {
		return siteDao.findByRelevance(query);
	}
	
	public PlacesUnion findPlacesUnion(String query) {
		return new PlacesUnion(findAllHotels(), findRelevantSites(query));
	}
	
	private <T> List<T> toList(Iterator<T> iterator) {
		List<T> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		return list;
	}

	// ======= GETTERS / SETTERS =======
	public HotelDao getHotelDao() {
		return hotelDao;
	}

	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	public SiteDao getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDao siteDao) {
		this.siteDao = siteDao;
	}

	public TransportDao getTransportDao() {
		return transportDao;
	}

	public void setTransportDao(TransportDao transportDao) {
		this.transportDao = transportDao;
	}
}

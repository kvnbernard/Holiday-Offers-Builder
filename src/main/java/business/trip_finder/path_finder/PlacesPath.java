package business.trip_finder.path_finder;

import java.util.ArrayList;
import java.util.List;

import business.trip.places.Hotel;
import business.trip.places.Place;
import business.trip.places.Site;

/**
 * An ordered path between first hotel, all sites and last hotel, 
 * without taking account of the transport.
 * 
 * @author Aldric Vitali Silvestre
 */
public class PlacesPath {

	private Hotel departureHotel;
	
	private List<Site> sitesBetween;
	
	private Hotel arrivalHotel;

	public PlacesPath(Hotel departureHotel, List<Site> sitesBetween, Hotel arrivalHotel) {
		super();
		this.departureHotel = departureHotel;
		this.sitesBetween = sitesBetween;
		this.arrivalHotel = arrivalHotel;
	}
	
	public List<Place> getFullPath() {
		List<Place> places = new ArrayList<>();
		places.add(departureHotel);
		places.addAll(sitesBetween);
		places.add(arrivalHotel);
		return places;
	}

	public Hotel getDepartureHotel() {
		return departureHotel;
	}

	public void setDepartureHotel(Hotel departureHotel) {
		this.departureHotel = departureHotel;
	}

	public List<Site> getSitesBetween() {
		return sitesBetween;
	}

	public void setSitesBetween(List<Site> sitesBetween) {
		this.sitesBetween = sitesBetween;
	}

	public Hotel getArrivalHotel() {
		return arrivalHotel;
	}

	public void setArrivalHotel(Hotel arrivalHotel) {
		this.arrivalHotel = arrivalHotel;
	}
}

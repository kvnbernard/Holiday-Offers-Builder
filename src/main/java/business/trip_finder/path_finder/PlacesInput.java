package business.trip_finder.path_finder;

import java.util.List;

import business.trip.places.Hotel;
import business.trip.places.Site;

/**
 * The input for the PathFinder. Similar to the class {@link PlacesPath}, this
 * one does not represent an ordered path.
 * 
 * @author Aldric Vitali Silvestre
 */
public class PlacesInput {
	private Hotel departureHotel;

	private List<Site> sites;

	private Hotel arrivalHotel;

	public PlacesInput(Hotel departureHotel, List<Site> sites, Hotel arrivalHotel) {
		super();
		this.departureHotel = departureHotel;
		this.sites = sites;
		this.arrivalHotel = arrivalHotel;
	}

	public Hotel getDepartureHotel() {
		return departureHotel;
	}

	public void setDepartureHotel(Hotel departureHotel) {
		this.departureHotel = departureHotel;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public Hotel getArrivalHotel() {
		return arrivalHotel;
	}

	public void setArrivalHotel(Hotel arrivalHotel) {
		this.arrivalHotel = arrivalHotel;
	}
}
